function  formatDate(now)   {     
	var year=now.getYear();     
    var month=now.getMonth()+1;     
    var date=now.getDate();     
    var hour=now.getHours();     
    var minute=now.getMinutes();     
    var second=now.getSeconds(); 
    if(month<10){month = "0"+month;}
    if(date<10){date = "0"+date;}      
    return year+"-"+month+"-"+date;     
} 