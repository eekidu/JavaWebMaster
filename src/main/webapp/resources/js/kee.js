/**
 * Created by wosyo on 2017/8/1.
 */


/**
 * Log工具类
 * @type {{d: Log.d}}
 */
var Log = {
    d: function (message) {
        console.log(message)
    },
    m: function (message) {
        console.log("-----" + message)
    }
}


var Person = {
    name: "caohaikuan",
    age: 16,
    toString: function () {
        var text = "";
        for (x in this) {
            text = text + this[x];
            return text;
        }
    }
}


