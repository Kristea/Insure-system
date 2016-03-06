/*JAVASCRIPT FOR REGISTER PAGE*/
/* Kristiana*/

/* ANIMATE REGISTER FORM
 * switch between the register forms for insured and insurer*/

//the form wrapper (includes all forms)
var $formWrap = $('#formWrap'),
//the current form is the one with class "active"
        $currentForm = $formWrap.children('form.active'),
//the switch form links
        $linkform = $formWrap.find('.linkform');

//store width and heights for animation 
$formWrap.children('form').each(function (i) {
    var $theForm = $(this);
    //solve the inline display none problem when using fadeIn/fadeOut
    if (!$theForm.hasClass('active'))
        $theForm.hide();
    $theForm.data({
        width: $theForm.width(),
        //height: $theForm.height()
    });
});

//call function that sets dimensions for wrapper
setWrapperWidth();

//what happens when switch link is clicked -
//current form is hidden, new one is bought up.
$linkform.bind('click', function (e) {
    var $link = $(this);
    var target = $link.attr('rel');
    $currentForm.fadeOut(200, function () {
        //remove class "active" from current form
        $currentForm.removeClass('active');
        //new current form
        $currentForm = $formWrap.children('form.' + target);
        //animate the wrapper
        $formWrap.stop()
                .animate({
                    width: $currentForm.data('width') + 'px',
                   // height: $currentForm.data('height') + 'px'
                }, 250, function () {
                    //new form gets class "active"
                    $currentForm.addClass('active');
                    //show the new form
                    $currentForm.fadeIn(400);
                });
    });
    e.preventDefault();
});
//set wrapper css properties
function setWrapperWidth() {
    $formWrap.css({
        width: $currentForm.data('width') + 'px',
       // height: $currentForm.data('height') + 'px'
    });
}


/*ALLOW SELECT BOXES TO CHANGE DEPENDING ON PREVIOUS SELECT*/
function listboxchange1(p_index) {
    //Clear Current options in make
    document.newUser.make.options.length = 0;

    //Clear Current options in model
    document.newUser.model.options.length = 0;
    document.newUser.model.options[0] = new Option("Select Model", "");

    switch (p_index) {

        case "Smartphone":
            document.newUser.make.options[0] = new Option("Select Make", "");
            document.newUser.make.options[0].disabled = true;

            document.newUser.make.options[1] = new Option("Apple iPhone", "Apple iPhone");
            document.newUser.make.options[2] = new Option("HTC", "HTC");
            document.newUser.make.options[3] = new Option("Samsung", "Samsung-p");
            document.newUser.make.options[4] = new Option("Sony", "Sony");
            break;

        case "Tablet":
            document.newUser.make.options[0] = new Option("Select Tablet Make", "");
            document.newUser.make.options[0].disabled = true;

            document.newUser.make.options[1] = new Option("Apple iPad", "Apple iPad");
            document.newUser.make.options[2] = new Option("Acer", "Acer-t");
            document.newUser.make.options[3] = new Option("Asus", "Asus-t");
            document.newUser.make.options[4] = new Option("LG", "LG");
            document.newUser.make.options[5] = new Option("Samsung", "Samsung-t");
            break;

        case "Laptop":
            document.newUser.make.options[0] = new Option("Select Laptop Make", "");
            document.newUser.make.options[0].disabled = true;

            document.newUser.make.options[1] = new Option("Acer", "Acer-l");
            document.newUser.make.options[2] = new Option("Asus", "Asus-l");
            document.newUser.make.options[3] = new Option("HP", "HP");
            document.newUser.make.options[4] = new Option("Lenovo", "Lenovo");
            break;

    }
    return true;
}

function listboxchange(p_index) {

    //Clear Current options in subtype
    document.newUser.model.options.length = 0;

    switch (p_index) {

        case "Apple iPhone":
            document.newUser.model.options[0] = new Option("Select Make", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("6", "6");
            document.newUser.model.options[2] = new Option("5s", "5s");
            break;

        case "Apple iPad":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.subcategor2.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("Air", "Air");
            document.newUser.model.options[2] = new Option("Air 2", "Air 2");
            document.newUser.model.options[3] = new Option("Mini", "Mini");
            break;

        case "HTC":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("One M8", "One M8");
            document.newUser.model.options[2] = new Option("Desire 620", "Desire 620");
            break;

        case "Samsung-p":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("Galaxy S5", "Galaxy S5");
            document.newUser.model.options[2] = new Option("Galaxy S6", "Galaxy S6");
            document.newUser.model.options[3] = new Option("Galaxy Note 3", "Galaxy Note 3");
            break;

        case "Samsung-t":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("Galaxy Tab s 8.5", "Galaxy Tab s 8.5");
            document.newUser.model.options[2] = new Option("Galaxy Note 8", "Galaxy Note 8");
            break;

        case "Sony":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("Xperia Z2", "Xperia Z2");
            document.newUser.model.options[2] = new Option("Xperia Z3", "Xperia Z3");
            break;

        case "LG":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("G Pad 10.1", "G Pad 10.1");
            document.newUser.model.options[2] = new Option("G Pad 7.8", "G Pad 7.8");
            break;

        case "Acer-t":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("Iconia Tab 8W", "Iconia Tab 8W");
            break;

        case "Acer-l":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("Aspire V3-371 13.3", "Aspire V3-371 13.3");
            document.newUser.model.options[2] = new Option("Aspire ES1-11M 11.6", "Aspire ES1-11M 11.6");
            document.newUser.model.options[3] = new Option("V Nitro Black Edition", "V Nitro Black Edition");
            break;

        case "Asus-t":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("Transformer Pad", "Transformer Pad");
            break;

        case "Asus-l":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("F555LA 15.6", "F555LA 15.6");
            document.newUser.model.options[2] = new Option("X553MA 15.6", "X553MA 15.6");
            break;

        case "Lenovo":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("Yoga3 Pro 13.3", "Yoga3 Pro 13.3");
            document.newUser.model.options[2] = new Option("U430 14 touch", "U430 14 touch");
            document.newUser.model.options[3] = new Option("Fluxx 2 15.6 Conv Touch", "Fluxx 2 15.6 Conv Touch");
            break;

        case "HP":
            document.newUser.model.options[0] = new Option("Select Model", "");
            document.newUser.model.options[0].disabled = true;

            document.newUser.model.options[1] = new Option("Omen 15-50Sona 15.6", "Omen 15-50Sona 15.6");
            document.newUser.model.options[2] = new Option("Pavillion 17-f254na 17.3", "Pavillion 17-f254na 17.3");
            break;

    }
    return true;
}
/*Check if user is over 18*/



/*additional form validation
$.validator.addMethod("valueNotEquals", function(value, element, arg){
  return arg !== value;
 }, "Value must not equal arg.");

 // configure your validation
 $("form").validate({
  rules: {
   SelectName: { valueNotEquals: "default" }
  },
  messages: {
   SelectName: { valueNotEquals: "Please select an item!" }
  }  
 });
 */