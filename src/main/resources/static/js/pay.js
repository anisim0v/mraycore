$().ready(function() {    
if(device.desktop() == true||device.tablet() == true) {
        $('#form').css('border-right', '1px solid black').css('margin-right', '30px');
    }
    else {
        $('#form').css('border-bottom', '1px solid black').css('margin-bottom', '20px');
    }
});