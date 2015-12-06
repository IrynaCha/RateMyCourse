/*jslint unparam:true, plusplus:true, nomen:true, node:true, es5:true */
/*global angular */

(function () {

    "use strict";

    angular.module('ratemyprofessor')

    .config(function ($routeProvider, $locationProvider) {

        $routeProvider

        .when('/', {
            templateUrl: 'views/universities.html'
        })
         .when('/university/:uid', {
            templateUrl: 'views/courses.html'
        })

        .when('/rate/:uid/:cid', {
            templateUrl: 'views/rate.html',
            controller:  'rateCtrl',
            controllerAs: 'ratectrl'
        })
        
   
        .otherwise({
            'redirectTo': '/'
        });

        $locationProvider.html5Mode(true);

    });

}());
