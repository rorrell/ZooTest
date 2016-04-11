/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function changeExhibitOptions(isInitial) {
    var selected;
    if(!isInitial) 
        $('#exhibit').html('<option value="">None</option>');
    else
        selected = $('#exhibit').val();
    var env = $('#compatibleEnvironment').val();
    if(env !== '') {
        var prefix = $.inArray('edit', 
            $(location).attr('href').split('/')) > -1 ? '../../' : '../';
        $.getJSON(prefix + "exhibits-by-environment?env=" + env,
            function (json) {
                $.each(json, function (index, value) {
                    $('select#exhibit option:last-child').after('<option value="' + value.id + '">' + value.name + '</option>');
                });
            }).then(function() {
                if(isInitial)
                    $('#exhibit').val(selected);
            });
    }
}
$(document).ready(function () {
    changeExhibitOptions(true);
    $('#compatibleEnvironment').change(function () {
        changeExhibitOptions(false);
    });
});