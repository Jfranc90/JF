$(document).ready(function(){
    $(".navbar-btn").click(function(){
        
        var nav = document.getElementById("navbar-bar")
        
        //If the "X" is showing when you click, then;
        if (nav.style.display === 'block') {
            nav.style.display = 'none'; //Remove the links
            $(".group-ham").removeClass("hidehamburger"); //Hamburger appears
            $(".navbar-btn").removeClass("exit-img"); //Remove X image
        } 
        
        //If the "hamburger" is showing when you click, then; 
        else if (nav.style.display = 'none') {
            nav.style.display = 'block'; //Show the links
            $(".group-ham").addClass("hidehamburger"); //Hide the hamburger
            $(".navbar-btn").addClass("exit-img"); //Show the X image
        }
    })
})
