package it.intervalle.portal.service;

import java.io.ByteArrayInputStream;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filenet.api.collection.ContentElementList;
import com.filenet.api.collection.ObjectStoreSet;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.core.ReferentialContainmentRelationship;

 
 
@Service
public class FilenetP8Services {
	Logger log = LoggerFactory.getLogger(FilenetP8Services.class);
	@Autowired
	FilenetService filenetService;
	
	 @SuppressWarnings("unused")
		public void uploadfiletoP8(String OS,String docname,String pth,byte[] b,String minetype) throws Exception
		 {
			 CEConnection c = new CEConnection();
			 c.establishConnection(filenetService.getUsername(), filenetService.getPassword(),filenetService.getContext(), filenetService.getUri());
			 Domain d= c.fetchDomain();
			 
			 log.info(d.get_Name());
			ObjectStore   objectStore =  c.fetchOS(OS);	 
		    Folder folder = Factory.Folder.fetchInstance(objectStore, pth, null);
		    Document doc = createDocWithContent( objectStore, docname, "",b,
		    		minetype);
		    ReferentialContainmentRelationship rcr = folder.file(doc, AutoUniqueName.AUTO_UNIQUE, docname,
					DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
			rcr.save(RefreshMode.REFRESH);
			 
			 
			 
			 
			 
			 ObjectStoreSet objset = c.getOSSet();
			 ObjectStore store;
		     Iterator osIter = objset.iterator();

		       while (osIter.hasNext()) 
		       {
		          store = (ObjectStore) osIter.next();
		          System.out.println("Object store: " + store.get_Name());
		       }
			
		 }
			@SuppressWarnings("unchecked")
			private Document createDocWithContent(ObjectStore os, String docName, String docClass,  byte[] b, String minetype)
					throws Exception {

				Document doc = null;
				if (docClass.equals(""))
					doc = Factory.Document.createInstance(os, null);
				
				else
					doc = Factory.Document.createInstance(os, docClass);
				doc.getProperties().putValue("DocumentTitle", docName);
				ContentElementList contentList = Factory.ContentElement.createList();
				try {
					if (b != null) {
						ContentTransfer ctNew = Factory.ContentTransfer.createInstance();
						ByteArrayInputStream is = new ByteArrayInputStream(b);
						ctNew.setCaptureSource(is);
						ctNew.set_ContentType(minetype);
						ctNew.set_RetrievalName(docName);
						contentList.add(ctNew);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				doc.set_ContentElements(contentList);
				doc.checkin(AutoClassify.AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
				doc.save(RefreshMode.REFRESH);
		 

				return doc;
			}
			

}
