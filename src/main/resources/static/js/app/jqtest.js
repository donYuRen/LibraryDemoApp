 $( function() {
    $( "#hometabs" ).tabs({

        beforeLoad: function(event, ui) {
    		if(ui.tab.attr("id") == 'lis_book') {
    			ui.ajaxSettings.method='GET'
    		    ui.ajaxSettings.url='http://localhost:8080/SpringBootLibraryApp/api/book/'
    			ui.ajaxSettings.dataFilter = function(data) {
    		        var jsonData = $.parseJSON(data);
    		        var htmlpanel = "";
    		        for (i=0; i < jsonData.length; i++)
    		        {
    		        	htmlpanel +="<li> Id: " + jsonData[i].id + " Name:"+ jsonData[i].name + " ISBN:" + jsonData[i].isbn + " Author:" +jsonData[i].author + "</li>"
    		        }

    		        ui.panel.html(htmlpanel);
    		      };
    		};
        	
        }
    	
    })
    	
  });