/**
 * Created by Jasmina on 15/01/2018.
 */
'use strict';

var app = angular.module('app', ['ui.router', 'ngMessages', 'ngMaterial', 'ngRoute'])
    .config(function ($routeProvider, $locationProvider, $mdThemingProvider) {

        $mdThemingProvider.theme('default')
            .primaryPalette('blue-grey')
            .accentPalette('blue');

        $routeProvider.when('/acquirer/success',{
            templateUrl: 'template/success.html',
            controller: 'SuccessController'
        });
        $routeProvider.when('/acquirer/error', {
            templateUrl: 'template/error.html',
            controller: 'ErrorController'
        });
        $routeProvider.when('/acquirer/fail', {
            templateUrl: 'template/fail.html',
            controller: 'FailController'
        });
        $routeProvider.when('/acquirer/order/:paymentId', {
            templateUrl: 'template/cardPayment.html',
            controller: 'CardPaymentController'
        });
    });