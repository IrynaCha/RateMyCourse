var app = angular.module('ratemyprofessor', ['ngRoute', 'ngMaterial']);
app.filter('reverse', function() {
	  return function(items) { if (!items || !items.length) { return; }
	    return items.slice().reverse();
	  };
	});

app.filter('objectLimitTo', function() {
    return function(obj, limit) {
        var result = {};

        var curr = 0;

        for (var key in obj) {
            if (obj.hasOwnProperty(key)) {
                result[key] = obj[key];

                if (++curr===limit) break;
            }
        }

        return result;
    };
});

