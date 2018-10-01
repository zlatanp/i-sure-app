administrator.service('tokenService', function(){
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

administrator.factory('httpRequestInterceptor',['tokenService', function (tokenService) {
	
		
	  return {
	    request: function (config) {
	    	var t = tokenService.getToken();
	      config.headers['X-XSRF-Token'] = t;
	       return config;
	    }
	  };
	}]);
//'$httpProvider', 'tokenService', '$http', 
administrator.config(function ($httpProvider) {
	
	
	$httpProvider.interceptors.push('httpRequestInterceptor');
	});



//'$scope', 'http', '$compile', '$timeout', '$rootScope', '$cookies', '$window', 'tokenService',

administrator.controller('Opsti', function($scope, $http, $compile, $timeout, $rootScope, $cookies, $window, tokenService){
	
	$scope.tab = 0;
	
	$scope.dr = -1;
	
	$scope.nm = -1;
	
	$scope.kl = -1;
	
	$scope.permisijeIzPermisija = [];
	
	$scope.token = "";
	
	$scope.showPhysical = -1;
	$scope.showLegal = -1;
	
	$scope.init = function(){
	
		
		$http.get('/special/getSafeToken').
		then(function mySucces(response) {
			
			
			if(angular.equals(response.data.name, 'OHNO')){
				$window.location.href=response.data.value;
			}
			
			tokenService.setToken(response.data.value);
		});
		
		$http.get('/special/getLoggedAndRole').
		then(function mySucces(response) {
			
			if(!response.data.logged){
				return;
			}
			
			if(angular.equals(response.data.name, 'MAN')){
				$scope.showLegal = 1;
			}else if(angular.equals(response.data.name, 'COU')){
				$scope.showPhysical = 1;
			}
			
		});
		
		
	
	};
	
//	this.doShowPhysical = function(){
//		if($scope.showPhysical){
//			return true;
//		}
//		
//		return false;
//	};
//	
//	this.doShowLegal = function(){
//		if($scope.showLegal){
//			return true;
//		}
//		
//		return false;
//	};
	
	
	
	this.probajZahtev = function(){
		
		
		$http.get('/special/checkToken', {headers: {'X-XSRF-TOKEN': $scope.token}}).
		then(function mySucces(response) {
			
			
		});
	}
	
	this.probajPostZahtev = function(){
		var variabla = "Nesto"
		$http({
		    method: 'POST',
		    url: '/special/checkPostToken',
		    data: variabla,
		    headers: {'X-XSRF-TOKEN': $scope.token}
		}).
		then(function mySucces(response) {
				
				
		});
	}
	
	this.logoff = function(){
		
		$http.get('/logoff').
		then(function mySucces(response) {
			
				//$window.location.href="http://localhost:8080/authentification/login.html";
				toastr.success("IZLOGOVAN");
				$window.location.href=response.data.username;
		});
	};

	this.tabClick = function(num){
		
		$scope.tab = num;
		
	};
	
	this.novoZatvaranje = function(){
		$scope.$broadcast('novoZatvaranje'); // going down!
	};
	
	this.tabClick2 = function(num, drzava){
		
		$scope.tab = num;
		
		if(angular.equals(drzava, {})){
			return;
		}
		
		$scope.dr = drzava.id;
		
		$scope.$broadcast('filterPoDrzavi', drzava.id); // going down!
	};
	
	this.tabClick5 = function(num, naseljenoMesto){
		
		$scope.tab = num;
		
		if(angular.equals(naseljenoMesto, {})){
			return;
		}
		
		$scope.nm = naseljenoMesto.id;
		
		$scope.$broadcast('filterPoNaseljenomMestuKlijent', naseljenoMesto.id); // going down!
	};
	
	this.tabClick6 = function(num, naseljenoMesto){
		
		$scope.tab = num;
		
		if(angular.equals(naseljenoMesto, {})){
			return;
		}
		
		$scope.nm = naseljenoMesto.id;
		
		$scope.$broadcast('filterPoNaseljenomMestuPravnoLice', naseljenoMesto.id); // going down!
	};
	
	this.tabClick7 = function(num, klijent){
		
		$scope.tab = num;
		
		if(angular.equals(klijent, {})){
			return;
		}
		
		$scope.kl = klijent.id;
		
		$scope.$broadcast('filterPoKlijentuRacun', klijent.id); // going down!
	};
	
	this.addRacun = function(){
		$scope.$broadcast('dodavanjeNovogRacuna');
	}
	
	this.tabClick8 = function(num, idSelektovanogRacuna){
		$scope.tab = num;
		$scope.$broadcast('filterZatvaranja', idSelektovanogRacuna); // going down!
	};
	
	this.tabClick10 = function(num, idSelektovanogRacuna){
		$scope.tab = num;
		$scope.$broadcast('filterDnevnihStanja', idSelektovanogRacuna); // going down!
	};
	
	this.tabClick11 = function(num, idSelektovanogStanja){
		$scope.tab = num;
		$scope.$broadcast('filterAnalitika', idSelektovanogStanja); // going down!
	};
	
	this.tabClickMedjubankarski11=function(num, medjubankarskiPrenos){
		$scope.tab = num;
		var stavke=[];
		
		$scope.$broadcast('filterPoStavkamaPrenosa', medjubankarskiPrenos); // going down!
	};
	
	this.importXMLFile=function(){
		//$http.post('/importChosenXML', scope[ attrs.name ][0].name).
		//	then(function mySucces(response) {
				
				//$scope.$emit('novaStanja');
				
		//	});
		$http({
			method: 'GET',
			url: '/importChosenXML'
		}).then(
			function success(response){
				toastr.success("Uspijesan import naloga");
			},
			function error(response){
				if(response.status==500){
					toastr.error("Doslo je do interne greske na serveru. Pokusajte ponovo");
				}else{
					toastr.success("Uspijesan import naloga");
				}
			}
		);
	}
	
});
/*
administrator.directive("filelistBind", function($http) {
	  return function( scope, elm, attrs ) {
	    elm.bind("change", function( evt ) {
	      //console.log( evt );
	      scope.$apply(function( scope ) {
	        scope[ attrs.name ] = evt.target.files;
	        
	        $http.post('/importChosenXML', scope[ attrs.name ][0].name).
			then(function mySucces(response) {
				
				//$scope.$emit('novaStanja');
				
			});
	        
	      });
	    });
	  };
	});
*/