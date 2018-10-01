angular
    .module('iSure')
    .run(["$state",
        function ($state) {
    	$state.go('homePage');  	
}]);
