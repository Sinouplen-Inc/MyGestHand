'use strict';

mygesthandJhipsterApp.controller('ContactController', ['$scope', 'resolvedContact', 'Contact',
    function ($scope, resolvedContact, Contact) {

        $scope.contacts = resolvedContact;

        $scope.create = function () {
            Contact.save($scope.contact,
                function () {
                    $scope.contacts = Contact.query();
                    $('#saveContactModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.contact = Contact.get({id: id});
            $('#saveContactModal').modal('show');
        };

        $scope.delete = function (id) {
            Contact.delete({id: id},
                function () {
                    $scope.contacts = Contact.query();
                });
        };

        $scope.clear = function () {
            $scope.contact = {id: null, birthDay: null, birthCity: null, address: null, postalCode: null,  city: null, job: null, phone: null, cellPhone: null, jobPhone: null, licenciate: false};
        };
    }]);


