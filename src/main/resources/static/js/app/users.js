$( function () {
   	  $( "#allUsers" ).accordion({
         collapsible: true
   	  });
   	  $( "button[id*='chk_bk_']" ).click(function () {
   		  var btn = $(this);
   	       var btnId = btn.attr('id');
   	       var userId = btnId.replace("chk_bk_", "");
   	       jQuery.ajax({
   	           url: 'http://localhost:8080/SpringBootLibraryApp/api/bookByUser/' + userId,
   	           success:function (response) {
   	        	   console.log(response);
   	               var contentDiv = btn.siblings("div" );
   	            console.log(contentDiv.attr('id'));
   	            var htmlContent ="Books:";
  	        	for (i=0; i < response.length; i++)
		        {
  	        		console.log(contentDiv.attr('id'));
   	               contentDiv.html("");
   	            htmlContent  =  htmlContent + "<li> Name:" + response[i].name + " Author:" + response[i].author + " ISBN:" + response[i].isbn + "</li>";
		        }
	               contentDiv.html(htmlContent);
	               $( "#allUsers" ).accordion("refresh");
   	           
   	           },
   	           error: function() {}
   	       });
   	  });
});