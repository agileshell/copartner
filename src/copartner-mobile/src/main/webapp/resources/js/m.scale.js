
var scale;
scale = {
    timer: null,
    lastTime: 1000 * 1.5,
    endTime: 1000 * 60,
    readyReg: /complete|loaded|interactive/,
    ready: function (callback) {

        if (this.readyReg.test(document.readyState) && document.body) {
            callback();
        } else {
            document.addEventListener('DOMContentLoaded', function () {
                callback();
            }, false);
        }
    },
    init: function () {

        this.scale();
        this.duration();

    },
    scale: function () {

        var oDiv = document.getElementById('scale-cont');
        var oParent = document.getElementById('scale-parent');


        var startWidth = oDiv.offsetWidth;
        var startHeight = oDiv.offsetHeight;


        var clentW = document.body.clientWidth-20;
        var startDivS = clentW / startWidth;

        oDiv.style.WebkitTransformOrigin = 0 + 'px ' + 0 + 'px';
        oDiv.style.transformOrigin = 0 + 'px ' + 0 + 'px';

        oDiv.style.WebkitTransform = 'scale(' + startDivS + ')';
        oDiv.style.transform = 'scale(' + startDivS + ')';

        oParent.style.width = startWidth * startDivS + 'px';
        oParent.style.height = startHeight * startDivS + 'px';
        oParent.style.overflow = 'hidden';

    },
    duration: function () {
        var that = this;

        that.timer = setInterval(function () {

            that.scale();

            setTimeout(function () {
                clearInterval(that.timer);
            }, that.endTime)

        }, that.lastTime);
    }
};


