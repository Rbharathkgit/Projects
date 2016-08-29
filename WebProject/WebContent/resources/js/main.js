function offSetManager(){
	var yoffset=0;
	var currOffset = window.pageYOffset;
	if(yoffset<currOffset){
		navBar.add();
	}
	else if(currOffset == yoffset){
		navBar.remove();
	}
}

function onchangeSubscriber(elem){
	var curvalue = $(elem).val();
	alert(curvalue);
	$(elem).attr('class').split(' ').forEach(function(item){
		if(item.indexOf('onchange-')!=-1){
			item.replace('onchange-','').split("-").forEach(function(itemtoshow){
				if(curvalue==''){$('#'+itemtoshow).hide();}
				else{$('#'+itemtoshow).show();}
			});
		}
	});
}

var activeWsno=3;

$(document).ready(function() {
	
	$('input.btn').css('white-space','normal');
	
	$(".wsName").keyup(function(){
	    $(".prjName").val(this.value);
	});
	
	$("input[type=radio]").checkboxradio({
      
    });
	
	$('[data-submenu]').submenupicker();
	
	$("input.mandatory,textarea.mandatory").each(function(){
		$(this).after("<span class='errspan' style='color:red;float: right;'></span>");
	});
	
	$("input.mandatory,textarea.mandatory").blur(function(){
		if($(this).val().trim()==''){
			$(this).parent().find('span.errspan').text('Mandatory');
		}else{
			$(this).parent().find('span.errspan').text('');
		}
	});
	
	$('.datepicker').attr('placeholder','MM/DD/YYYY');
	
	/**
	 * HomePage workspace search
	 */
//	$("select.")
	
	$("select.workspace-sieve").hide();
	$(".allws").hide();
	$(".emptyws").hide();
	$("select.workspace-sieve > option").each(function(index) {
	    if(index<activeWsno){
	    	$(".activews").append("<dt class='wslink' ><a href='#'>"+$(this)[0].outerHTML+"</a></dt>");
	    }
	    $(".allws").append("<dt class='wslink'><a href='#'>"+$(this)[0].outerHTML+"</a></dt>");
	});
	
	$("dt.wslink").click(function(){
		$('#wsModal').modal({backdrop: 'static', keyboard: false});
		var option = $(this).find('option');
		$("select.workspace-sieve > option[value="+option.val()+"]").attr('selected',true);
		$(".modal-title").html('<b>'+option.text()+'</b>');
	});
	
	$("div.workspace-sieve").sieve({ 
		itemSelector: "dt",
		searchTemplate:  $("#searchws").html()
	});
	
	/**
	 * HomePage workspace search - END
	 */
	
	$('.datepicker').datepicker();
	
//	$(".select2").chosen();
	$("select.select2").select2();
	/*$(".select2-newws").select2({
		"language": {
			"noResults": function(){
				return "No Results Found <a id='createwsbtn' href='#'>Create New One</a>";
			}
		},
		escapeMarkup: function (markup) {
			return markup;
		}

	});*/
	
	if (typeof jsf !== 'undefined') {
		jsf.ajax.addOnEvent(afterJSFRender);
	}
	
	$(".createwscontent").hide();
	$(".createwscontent-hide").show();
	$("#createwsbtn").click(function(){
		alert('sdasad');
		$(".createwscontent").show();
		$(".createwscontent-hide").hide();
		$(".newWSflag").attr("value","true");
	});
	$("#selectwsbtn").click(function(){
		$(".createwscontent").hide();
		$(".createwscontent-hide").show();
		$(".newWSflag").attr("value","false");
	});

	$(".depends").hide();
	$("select[class^='onchange-'],select[class*=' onchange-']").on("change",function(event){
		var curvalue = $(this).val();
		alert(curvalue);
		$(this).attr('class').split(' ').forEach(function(item){
			if(item.indexOf('onchange-')!=-1){
				item.replace('onchange-','').split("-").forEach(function(itemtoshow){
					if(curvalue==''){$('#'+itemtoshow).hide();}
					else{$('#'+itemtoshow).show();}
				});
			}
		});
	});
	
	function afterJSFRender(data){
        var ajaxStatus = data.status; // Can be "begin", "complete" and "success".
        switch (ajaxStatus) {
            case "begin": // Right before sending ajax request.
            	$("select.select2.depends").each(function(idx,item){
            		$(item).select2('destroy');
            	});
                break;

            case "complete": // Right after receiving ajax response.
                break;

            case "success": // When ajax response is successfully processed.
            	$("select.select2.depends").each(function(idx,item){
            		$(item).select2();
            	});
                break;
        }

	}
	
	var wsdialog,wsform,clientName;
	
	// get the screen height and width  
	var maskHeight = $(document).height();  
	var maskWidth = $(window).width();
	
	// calculate the values for center alignment
	var dialogTop =  (maskHeight/3) - ($('#dialog-box').height());  
	var dialogLeft = (maskWidth/2) - ($('#dialog-box').width()/2); 
	
	// assign values to the overlay and dialog box
	$('#dialog-overlay').css({height:maskHeight, width:maskWidth});
	$('#dialog-box').css({top:dialogTop, left:dialogLeft});
	
    $.ajax({
       	url: "../resources/json/testingJSON.json",
       	dataType: "text",
	    success: function (dataTest) {

	        // data downloaded so we call parseJSON function
	        // and pass downloaded data
	        var json = $.parseJSON(dataTest);
	        // now json variable contains data in json format
	        // let's display a few items
	        $.each(json, function (i, jsonObjectList) {
	        for (var index = 0; index < jsonObjectList.listValue_.length;index++) {
	              //alert(jsonObjectList.listKey_[index][0] + " -- " + jsonObjectList.listValue_[index].description_);
	              }
	         });


	     }
  });

    $.getJSON('../resources/json/testingJSON.json', function(data) {
//	  alert(data);
	});

wsdialog = $( "#workspaceDialog" ).dialog({
    autoOpen: false,
    dialogClass: "no-close",
    title: "Select Workspace",
    height: 400,
    width: 450,
    modal: true,
    buttons: {
    	"Create New" : addWorkspace,
    	Cancel: function() {
    		wsdialog.dialog( "close" );
    	}
    },
    close: function() {
    	wsform[0].reset();
    }
});

wsform = wsdialog.find( "form" ).on( "submit", function( event ) {
    event.preventDefault();
    addWorkspace();
 });


$(".client").click(function(element){
	wsdialog.dialog("open");
});


function addWorkspace(){
	alert("Add workspace");
}
    
    $( ".scenarioId" ).autocomplete({
      	source: availableTags
    });
    
    window.onscroll = function(e){
    	offSetManager();
    };

    offSetManager();
    
       
});

//Popup dialog
function popup(message) {
		
	// get the screen height and width  
	var maskHeight = $(document).height();  
	var maskWidth = $(window).width();
	
	// calculate the values for center alignment
	var dialogTop =  (maskHeight/3) - ($('#dialog-box').height());  
	var dialogLeft = (maskWidth/2) - ($('#dialog-box').width()/2); 
	
	// assign values to the overlay and dialog box
	$('#dialog-overlay').css({height:maskHeight, width:maskWidth}).show();
	$('#dialog-box').css({top:dialogTop, left:dialogLeft}).show();
	
	// display the message
	$('#dialog-message').html(message);
			
}


var availableTags = [
                     "Scenario_check current date",
                     "Scenario_check loan amount",
                     "Scenario_validate bubd",
                     "Scenario contract 102",
                     "contract failure",
                     "loan id 265",
                     "test recalc itr 12",
                     "COBOL",
                     "ColdFusion",
                     "Erlang",
                     "Fortran",
                     "Groovy",
                     "Haskell",
                     "Java",
                     "JavaScript",
                     "Lisp",
                     "Perl",
                     "PHP",
                     "Python",
                     "Ruby",
                     "Scala",
                     "Scheme"
                   ];


/**
 * NAVBAR - MENU SCRIPTS
 */ 

var navBar = {
		flagAdd:true,
		elements:[],
		init: function(elements){
			this.elements = elements;
		},
		add: function(){
			if(this.flagAdd){
				for(var i=0;i<this.elements.length;i++){
					$("#"+this.elements[i]).className+=" fixed-theme";
				}
				this.flagAdd=false;
			}
		},
		remove:function(){
			for(var i=0;i<this.elements.length;i++){
				if(!$("#"+this.elements[i])){
					$("#"+this.elements[i]).className =
						$("#"+this.elements[i]).className.replace(/(?:^|\s)fixed-theme(?!\S)/g,'');
				}
			}
			this.flagAdd=true;
		}
};

navBar.init([
             "navbar-header",
             "header-container",
             "brand"
]);

/**
 * NAVBAR - MENU SCRIPTS - ENDS
 */

/**
 * Dialog Panel for Worspace selection
 */








/**
 * Dialog Panel for Worspace selection - END
 */






/*!
 * jQuery Sieve v0.3.0 (2013-04-04)
 * http://rmm5t.github.io/jquery-sieve/
 * Copyright (c) 2013 Ryan McGeary; Licensed MIT
 */
(function() {
  var $;

  $ = jQuery;

  $.fn.sieve = function(options) {
    var compact;
    compact = function(array) {
      var item, _i, _len, _results;
      _results = [];
      for (_i = 0, _len = array.length; _i < _len; _i++) {
        item = array[_i];
        if (item) {
          _results.push(item);
        }
      }
      return _results;
    };
    return this.each(function() {
      var container, searchBar, settings;
      container = $(this);
      settings = $.extend({
        searchInput: null,
        searchTemplate: "<div><label>Search: <input type='text'></label></div>",
        itemSelector: "tbody tr",
        textSelector: null,
        toggle: function(item, match) {
          return item.toggle(match);
        },
        complete: function() {}
      }, options);
      if (!settings.searchInput) {
        searchBar = $(settings.searchTemplate);
        settings.searchInput = searchBar.find("input");
        container.before(searchBar);
      }
      return settings.searchInput.on("keyup.sieve change.sieve", function() {
        var items, query;
        query = compact($(this).val().toLowerCase().split(/\s+/));
        $(".emptyws").hide();
        if(query==''){
        	$('.allws').hide();
        	$('.activews').show();
        }
        else{
        	$('.allws').show();
        	$('.activews').hide();
        }
        items = container.find(settings.itemSelector);
        var shown = 0;
        items.each(function() {
          var cells, item, match, q, text, _i, _len;
          item = $(this);
          if (settings.textSelector) {
            cells = item.find(settings.textSelector);
            text = cells.text().toLowerCase();
          } else {
            text = item.text().toLowerCase();
          }
          match = true;
          for (_i = 0, _len = query.length; _i < _len; _i++) {
            q = query[_i];
            match && (match = text.indexOf(q) >= 0);
          }
          if(match)shown=shown+1;
          return settings.toggle(item, match);
        });
        if(shown===0){
        	$('.allws').hide();
        	$('.activews').hide();
        	$(".emptyws").show();
        }
        return settings.complete();
      });
    });
  };

}).call(this);
