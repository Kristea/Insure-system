/*ALLOW SELECT BOXES TO CHANGE DEPENDING ON PREVIOUS SELECT*/
function listboxchange1(p_index) {
    //Clear Current options in make
    document.addInsReq.make.options.length = 0;

    //Clear Current options in model
    document.addInsReq.model.options.length = 0;
    document.addInsReq.model.options[0] = new Option("Select Model", "");

    switch (p_index) {

        case "Smartphone":
            document.addInsReq.make.options[0] = new Option("Select Make", "");
            document.addInsReq.make.options[0].disabled = true;

            document.addInsReq.make.options[1] = new Option("Apple iPhone", "Apple iPhone");
            document.addInsReq.make.options[2] = new Option("HTC", "HTC");
            document.addInsReq.make.options[3] = new Option("Samsung", "Samsung-p");
            document.addInsReq.make.options[4] = new Option("Sony", "Sony");
            break;

        case "Tablet":
            document.addInsReq.make.options[0] = new Option("Select Tablet Make", "");
            document.addInsReq.make.options[0].disabled = true;

            document.addInsReq.make.options[1] = new Option("Apple iPad", "Apple iPad");
            document.addInsReq.make.options[2] = new Option("Acer", "Acer-t");
            document.addInsReq.make.options[3] = new Option("Asus", "Asus-t");
            document.addInsReq.make.options[4] = new Option("LG", "LG");
            document.addInsReq.make.options[5] = new Option("Samsung", "Samsung-t");
            break;

        case "Laptop":
            document.addInsReq.make.options[0] = new Option("Select Laptop Make", "");
            document.addInsReq.make.options[0].disabled = true;

            document.addInsReq.make.options[1] = new Option("Acer", "Acer-l");
            document.addInsReq.make.options[2] = new Option("Asus", "Asus-l");
            document.addInsReq.make.options[3] = new Option("HP", "HP");
            document.addInsReq.make.options[4] = new Option("Lenovo", "Lenovo");
            break;

    }
    return true;
}

function listboxchange(p_index) {

    //Clear Current options in subtype
    document.addInsReq.model.options.length = 0;

    switch (p_index) {

        case "Apple iPhone":
            document.addInsReq.model.options[0] = new Option("Select Make", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("6", "6");
            document.addInsReq.model.options[2] = new Option("5s", "5s");
            break;

        case "Apple iPad":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.subcategor2.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("Air", "Air");
            document.addInsReq.model.options[2] = new Option("Air 2", "Air 2");
            document.addInsReq.model.options[3] = new Option("Mini", "Mini");
            break;

        case "HTC":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("One M8", "One M8");
            document.addInsReq.model.options[2] = new Option("Desire 620", "Desire 620");
            break;

        case "Samsung-p":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("Galaxy S5", "Galaxy S5");
            document.addInsReq.model.options[2] = new Option("Galaxy S6", "Galaxy S6");
            document.addInsReq.model.options[3] = new Option("Galaxy Note 3", "Galaxy Note 3");
            break;

        case "Samsung-t":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("Galaxy Tab s 8.5", "Galaxy Tab s 8.5");
            document.addInsReq.model.options[2] = new Option("Galaxy Note 8", "Galaxy Note 8");
            break;

        case "Sony":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("Xperia Z2", "Xperia Z2");
            document.addInsReq.model.options[2] = new Option("Xperia Z3", "Xperia Z3");
            break;

        case "LG":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("G Pad 10.1", "G Pad 10.1");
            document.addInsReq.model.options[2] = new Option("G Pad 7.8", "G Pad 7.8");
            break;

        case "Acer-t":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("Iconia Tab 8W", "Iconia Tab 8W");
            break;

        case "Acer-l":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("Aspire V3-371 13.3", "Aspire V3-371 13.3");
            document.addInsReq.model.options[2] = new Option("Aspire ES1-11M 11.6", "Aspire ES1-11M 11.6");
            document.addInsReq.model.options[3] = new Option("V Nitro Black Edition", "V Nitro Black Edition");
            break;

        case "Asus-t":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("Transformer Pad", "Transformer Pad");
            break;

        case "Asus-l":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("F555LA 15.6", "F555LA 15.6");
            document.addInsReq.model.options[2] = new Option("X553MA 15.6", "X553MA 15.6");
            break;

        case "Lenovo":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("Yoga3 Pro 13.3", "Yoga3 Pro 13.3");
            document.addInsReq.model.options[2] = new Option("U430 14 touch", "U430 14 touch");
            document.addInsReq.model.options[3] = new Option("Fluxx 2 15.6 Conv Touch", "Fluxx 2 15.6 Conv Touch");
            break;

        case "HP":
            document.addInsReq.model.options[0] = new Option("Select Model", "");
            document.addInsReq.model.options[0].disabled = true;

            document.addInsReq.model.options[1] = new Option("Omen 15-50Sona 15.6", "Omen 15-50Sona 15.6");
            document.addInsReq.model.options[2] = new Option("Pavillion 17-f254na 17.3", "Pavillion 17-f254na 17.3");
            break;

    }
    return true;
}
