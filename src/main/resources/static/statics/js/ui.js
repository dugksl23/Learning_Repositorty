$(function(){
	$(window).resize(function () {
		var width = $(window).width();
		if (width>=1024) {
			$('[data-toggle="offcanvas"]').on('mouseenter', function () {
				$('.offcanvas-collapse').toggleClass('action');
				$('.wrapper').toggleClass('action');
			});
			$('.offcanvas-collapse').on('mouseleave', function () {
				$('.offcanvas-collapse').toggleClass('action');
				$('.wrapper').toggleClass('action');
			});
		}else{
			$('[data-toggle="offcanvas"]').on('click', function () {
				$('.offcanvas-collapse').toggleClass('action');
				$('.wrapper').toggleClass('action');
			});
		}
	});
	
	$(window).trigger("resize");
	
	$(window).resize(function(){
		location.reload();
	});
});

$(function () {
	$(window).scroll(function () {
			if ($(this).scrollTop() > 20) {
				$('.scroll-top').fadeIn();
			} else {
				$('.scroll-top').fadeOut();
			}
		});
		$('.scroll-top').click(function () {
			$('body,html').animate({
				scrollTop: 0
			}, 500);
			return false;
		});
});

$('.dropdown-toggle').dropdown();

$(function () {
  $('[data-toggle="left-side"]').on('click', function () {
    $('.left-side').toggleClass('action');
  });
});





