/*TOGGLE EACH SECTION WHEN USER CLICKS*/

var main = function () {
  
    $('.sectionheader').click(function () {
        $('.sectionheader').removeClass('current');
        $('.section').hide();

        $(this).addClass('current');
        $(this).children('.section').show();

        $('.sectionheader .current').click(function () {
            $('.section').toggle();
        });

    });

};
$(document).ready(main);

