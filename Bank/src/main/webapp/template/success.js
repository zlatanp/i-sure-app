/**
 * Created by Jasmina on 15/01/2018.
 */
app.controller('SuccessController', function ($scope, $state, cardPaymentService){

    var loadName = function () {
        cardPaymentService.getBankName(function(response){
            $scope.bankName = response.data.name;
        });
    }

    loadName();

    $scope.imagePath = "image/success.png";
    $scope.cardPath = "image/cards.png";
});