
updateList = function() {
      var input = document.getElementById('file');
      var output = document.getElementById('divFiles');
      var filelist = new Array();
      var HTML = "<table align='center' id='filetab'>";
      for (var i = 0; i < input.files.length; ++i) {
          filelist[i]=input.files.item(i).name;
          HTML += "<tr><td>" + filelist[i] + "</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' class='remove-button' onclick='deleteRow("+i+")'>X</button></td></tr>";
      }
      HTML += "</table>";
      output.innerHTML += HTML;
 
      
  }
function deleteRow(i) {
	 var input = document.getElementById('file');


	 console.log(input);
	 
	  document.getElementById("filetab").deleteRow(i);
	  console.log("hi");
	}