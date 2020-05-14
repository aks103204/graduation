/*
 * metismenu - v1.1.3
 * Easy menu jQuery plugin for Twitter Bootstrap 3
 * https://github.com/onokumus/metisMenu
 *
 * Made by Osman Nuri Okumus
 * Under MIT License
 */
;(function($, window, document, undefined) {

    var pluginName = "metisMenu",
        defaults = {
            toggle: true,
            doubleTapToGo: false
        };

    function Plugin(element, options) {
        this.element = $(element);
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this._name = pluginName;
        this.init();
    }

    Plugin.prototype = {
        init: function() {

            var $this = this.element,
                $toggle = this.settings.toggle,
                obj = this;
            var ieVer = this.isIE();
            if (ieVer <= 9) {
                //$this.find("li.active").has("ul").children("ul").collapse("show");
                //$this.find("li").not(".active").has("ul").children("ul").collapse("hide");
            	$this.find('li.active').has('ul').children('ul').addClass('metis-open').css('display', 'block');
                $this.find('li').not('.active').has('ul').children('ul').addClass('metis-close').css('display', 'none');
            } else {
                $this.find("li.active").has("ul").children("ul").addClass("collapse in");
                $this.find("li").not(".active").has("ul").children("ul").addClass("collapse");
            }

            //add the "doubleTapToGo" class to active items if needed
            if (obj.settings.doubleTapToGo) {
                $this.find("li.active").has("ul").children("a").addClass("doubleTapToGo");
            }

            $this.find("li").has("ul").children("a").on("click" + "." + pluginName, function(e) {
                e.preventDefault();

                //Do we need to enable the double tap
                if (obj.settings.doubleTapToGo) {

                    //if we hit a second time on the link and the href is valid, navigate to that url
                    if (obj.doubleTapToGo($(this)) && $(this).attr("href") !== "#" && $(this).attr("href") !== ""&& $(this).attr("href") !== "javascript:void(0);") {
                        e.stopPropagation();
                        document.location = $(this).attr("href");
                        return;
                    }
                }

                if (ieVer <= 9) {
                    $(this).parent('li').toggleClass('active').children('ul').slideToggle().toggleClass('metis-close').toggleClass('metis-open');

                    if ($toggle) {
                        $(this).parent('li').siblings().removeClass('active').children('ul.metis-open').slideUp().toggleClass('metis-open');
                    }
                } else {
                    $(this).parent("li").toggleClass("active").children("ul").collapse("toggle");

                    if ($toggle) {
                        $(this).parent("li").siblings().removeClass("active").children("ul.in").collapse("hide");
                    }
                }
            });
        },

        isIE:function(){

            var undef, v = 3, div = document.createElement('div');

            while (
                div.innerHTML = '<!--[if gt IE '+(++v)+']><i></i><![endif]-->',
                div.getElementsByTagName('i')[0]
            );
            return v > 4 ? v : undef;

        },


        //Enable the link on the second click.
        doubleTapToGo: function(elem) {
            var $this = this.element;

            //if the class "doubleTapToGo" exists, remove it and return
            if (elem.hasClass("doubleTapToGo")) {
                elem.removeClass("doubleTapToGo");
                return true;
            }

            //does not exists, add a new class and return false
            if (elem.parent().children("ul").length) {
                 //first remove all other class
                $this.find(".doubleTapToGo").removeClass("doubleTapToGo");
                //add the class on the current element
                elem.addClass("doubleTapToGo");
                return false;
            }
        },

        remove: function() {
            this.element.off("." + pluginName);
            this.element.removeData(pluginName);
        }

    };

    $.fn[pluginName] = function(options) {
        this.each(function () {
            var el = $(this);
            if (el.data(pluginName)) {
                el.data(pluginName).remove();
            }
            el.data(pluginName, new Plugin(this, options));
        });
        return this;
    };

})(jQuery, window, document);