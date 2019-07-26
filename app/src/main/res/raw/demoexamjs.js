
	$(document).ready(function(){
		//document.oncontextmenu = document.body.oncontextmenu = function() {return false;}
	
		$('.endexam').click(function(){
			
			$('#submitModal').modal({
            backdrop: 'static',
            keyboard: false
        });
			StopFunction(interval);
		})
		$('.report').click(function(){
			$('#msg_error').html(" ");
			$('#q_message').html(" ");
			$('#report_modal').modal("show");

		})
		
	    $('head').append("<style>body { background-color: grey; }</style>");
	})
	function show_report_modal()
	{
		$('#msg_error').html(" ");
      	$('#q_message').html(" ");
      	$('#report_modal').modal("show");
	}
	function submit_report()
	{
		var id = $(".question:visible").attr("data-id");
			var baseurl = $('#baseurl').val();
			var msg = $('#q_message').val();
		
			if(msg=="")
			{
				$('#msg_error').html("Please enter the message");
			}
			else
			{

				$('#msg_error').html("");
				 $.ajax({
                  url: baseurl+"DemoMock/reportExam",
                  type:"post",
                  data:{"id":id,"msg":msg},
                  success:function(result)
                  {  
                  	console.log(result);
                     if(result==1)
                     {
                     	$('.result_msg').html('<div class="alert alert-success">'+
  									'<strong>Success!</strong> You have successfully send your message.'+
												'</div>');
                     	
						
						$('#q_message').val("");
						
						
                     }
                     else
                     	{
                     	$('.result_msg').html('<div class="alert alert-warning">'+
  									'<strong>Sorry!</strong> You have failed to send your message.'+
												'</div>');
                     }
                  }
              });
			}
	}
	function set_value(id,value)
	{
		var e= "option_val"+id;
		$('#'+e).val(value);
	}
	function complete()
	{
		$('#submitModal').modal('hide');
		$( "#exam-form" ).submit();
            
	}
	function resume()
	{
        var minutes = getCookie("minutes");
		var fiveMinutes = 60 * 10;
		var hur = parseInt($('#hur').html());
		var min = parseInt($('#min').html());
		var sec = parseInt($('#sec').html());
		var tot = hur*3600 + min*60 + sec;
		display0 = document.querySelector('#hur');
		display1 = document.querySelector('#min');
  		display2 = document.querySelector('#sec');
 		startTimer(tot, display0,display1,display2);
		$('#submitModal').modal('hide');
	}
	function getTimeTaken()
	{
                var minutes = getCookie("minutes");
		var fiveMinutes = 60*10;
		var min = parseInt($('#min').html());
		var sec = parseInt($('#sec').html());
  		var timer = sec, min, sec;

  		var time_taken = fiveMinutes-timer;
  		var perc = (time_taken/fiveMinutes)*100;
  		var minutes = parseInt(time_taken / 60, 10);
        var seconds = parseInt(time_taken % 60, 10);
        var t = minutes +"m "+seconds+"s"+"-"+perc;	
        console.log(t);
        return t;
	}
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}