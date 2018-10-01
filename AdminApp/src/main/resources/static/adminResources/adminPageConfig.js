var administratorBanke = angular.module('administratorBanke', ['ui.router']);
/**
administratorBanke.service('tokenService', function(){
	var token = "";
	
	var setToken = function(vrednost){
		token = vrednost;
	}
	
	var getToken = function(){
		return token;
	}

	return {
		setToken : setToken,
		getToken : getToken
	};
	
});

administratorBanke.factory('httpRequestInterceptor',['tokenService', function (tokenService) {
	
		
	  return {
	    request: function (config) {
	    	var t = tokenService.getToken();
	      config.headers['X-XSRF-Token'] = t;
	       return config;
	    }
	  };
	}]);

*/


administratorBanke.config(function($stateProvider, $httpProvider) {
    
	var homeState = {
		    name: 'home',
		    url: '/home',
		    template: '<h3>hello world!</h3>'
		  }

	var permisijeState = {
		    name: 'permisije',
		    url: '/permisije',
		    templateUrl: 'roleIpermisije/Permisije.html'
		  }

	var roleState = {
		    name: 'role',
		    url: '/role',
		    templateUrl: 'roleIpermisije/Role.html'
		  }

	
	
	$stateProvider.state(homeState);
	$stateProvider.state(permisijeState);
	$stateProvider.state(roleState);
	
	//$httpProvider.interceptors.push('httpRequestInterceptor');
});

administratorBanke.controller('Opsti', function($window, $scope, $http, $compile) {
	
	$scope.init = function(){
	/**
		
		$http.get('/special/getSafeToken').
		then(function mySucces(response) {
			
			
			if(angular.equals(response.data.name, 'OHNO')){
				$window.location.href=response.data.value;
			}
			
			tokenService.setToken(response.data.value);
		});*/
	
	};
	
	/**
	this.goToApp = function(){
		$http.get('/goToApp').
		then(function mySucces(response) {
			
				$window.location.href=response.data.username;
		});
	}
	
	this.logoff = function(){
		
		$http.get('/logoff').
		then(function mySucces(response) {
			
			
				toastr.success("IZLOGOVAN");
				$window.location.href=response.data.username;
		});
	};*/
});
