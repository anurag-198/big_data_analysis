function askCourse(chkBoxRadioFlg, opFlag){
alert("IN ASK COURSE")
	url= "PopulateCourseList";
	if (window.XMLHttpRequest){
	   	xmlhttp=new XMLHttpRequest();
	}
	else {
   		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function(){
 	  	if (xmlhttp.readyState==4 && xmlhttp.status==200){
 	  		//alert("xmlhttp.responseText " + xmlhttp.responseText);
 	  		courseList = JSON.parse(xmlhttp.responseText);
 	  		
 	  		if(chkBoxRadioFlg == "C");
 	  			courseList.push("All Courses");
	 	  		//alert("in populate courseList " + courseList)
 	  			populateCourse('courseListId', chkBoxRadioFlg, opFlag); //,courseList);
	 	  		if (opFlag == 'DR')
	 				toggle_visibility('dropRateId',1); 
	 	}
	}
   	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}
function populateCourse(courseNode, chkBoxRadioFlg, InteractionType){ //}, courseList){
	var cb;
	var textnode;
	var arr = document.getElementById(courseNode); //courseListHorId');
	while (arr.firstChild) {
	   arr.removeChild(arr.firstChild);
	}
	for(i in courseList){
		cb = document.createElement('input');
  		   if(chkBoxRadioFlg == 'C')
  		    	cb.type = 'checkbox';
  		    else
  		    	cb.type = 'radio';
  		    cb.name = 'course';
  		    cb.value = courseList[i];
  		    cb.id = courseList[i];
		
	      // alert("courseList[i] " + courseList[i]);
  		    if( (InteractionType == 'CI') || (InteractionType == 'VD') || (InteractionType == 'PB')){
  		      cb.onclick = function() {
  		  		toggle_visibility('bkgrndParamGrp',0);
  				toggle_visibility('coursePlotTypes',0);
  				toggle_visibility('perfAnalParamGrp',0);
  				toggle_visibility('miscAnalGrp',0);
  				//toggle_visibility('courseListId',0);
  				//alert(this.value);
  				document.getElementById('courseNameId').value = this.value;
  				askChapter(this.value,InteractionType );
  		      };
  		    }
  		    else if((InteractionType == 'FT') || (InteractionType == 'FD')){
  		      cb.onclick = function() {
	  				document.getElementById('courseNameId').value = this.value;
	  				askDiscussion(this.value,InteractionType );
	  		      };
  		    } else if((InteractionType == 'RG') || (InteractionType == 'SD')) {
    		      cb.onclick = function() {
   	  				document.getElementById('courseNameId').value = this.value;
   	  		      };
    		    }
  		arr.appendChild(cb);                              // Append the text to <li>
		arr.appendChild(document.createTextNode(courseList[i]));
		arr.appendChild(document.createElement("br"));
  	}
}

