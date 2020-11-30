(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run();

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'about/about.html'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            });
    }
})();
