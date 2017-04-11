$(document).ready(function() {
    document.querySelector( "form" )
        .addEventListener( "invalid", function( event ) {
            event.preventDefault();
            $(":invalid")[1].focus();
        }, true );

    $('#noaccount').change(function() {
        if ($('#noaccount').prop('checked')) {
            $('#reg_acc').show();
            $('#account').val('').prop('disabled', true).hide();
            $(".account").hide();
            $("#agree, .agree").hide();
        }
        else {
            $("#reg_acc").val('').hide();
            $('#account').prop('disabled', false).show();
            $(".account").show();
            $("#agree, .agree").show();
        }
    });
    if(device.desktop()||device.tablet()) {
        $('#buy-form').css('border-right', '1px solid black').css('margin-right', '30px');
    }
    else {
        $('#buy-form').css('border-bottom', '1px solid black').css('margin-bottom', '20px');
    }
    if ($('#noaccount').prop('checked')) {
    $('#reg_acc').show();
    $('#account').val('').prop('disabled', true);
        $("#agree, .agree").hide();
    }
    else {
        $("#reg_acc").val('').hide();
        $('#account').prop('disabled', false);
        $("#agree, .agree").show();
    }
    $('input').change(function(){
        if($("#email").val()!=""&&$('#twiceayear').prop('checked')&&(((!($('#noaccount').prop('checked'))&&$('#agree').prop('checked')&&$("#account").val()!=""))||($('#noaccount').prop('checked')&&$("#reg_acc").val()!=""))){
            $('#submit').attr('disabled',false).removeClass('button').addClass('button-primary');
        }
        else{
            $('#submit').removeClass('button-primary').addClass('button').attr('disabled',true);
        }
    });
	/*$("#country, #timefor").change(function() {
		switch($("#country option:checked").val()){
			case "US":
				$("#submit").html("Попробовать! ("+210*$('#timefor option:checked').val()+"р.)");
				break;
			case "PH":
				$("#submit").html("Попробовать! ("+60*$("#timefor option:checked").val()+"р.)");
				break;
		}
	});
	switch($("#country option:checked").val()){
			case "US":
				$("#submit").html("Попробовать! ("+210*$("#timefor option:checked").val()+"р.)");
				break;
			case "PH":
				$("#submit").html("Попробовать! ("+60*$("#timefor option:checked").val()+"р.");
				break;
		}*/
    $("#account").hide();
    $(".account").hide();
});
