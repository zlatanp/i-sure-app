/**
 * Created by Jasmina on 16/01/2018.
 */
app.controller('ErrorController', function ($scope, $state, cardPaymentService){

    var loadName = function () {
        cardPaymentService.getBankName(function(response){
            $scope.bankName = response.data.name;
        });
    }

    loadName();

    $scope.imagePath = "image/error.png";
    $scope.cardPath = "image/cards.png";
});