$.fn.stars = function() {
    return this.each(function(i,e){$(e).html($('<span/>').width($(e).text()*50));});
};

$('.stars').stars();