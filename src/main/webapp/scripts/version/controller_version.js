'use strict';

mygesthandJhipsterApp.controller('VersionController', function ($scope, resolvedVersion, Version) {

    $scope.versions = resolvedVersion;

    $scope.create = function () {
        Version.save($scope.version,
            function () {
                $scope.versions = Version.query();
                $('#saveVersionModal').modal('hide');
                $scope.clear();
            });
    };

    $scope.update = function (id) {
        $scope.version = Version.get({id: id});
        $('#saveVersionModal').modal('show');
    };

    $scope.delete = function (id) {
        Version.delete({id: id},
            function () {
                $scope.versions = Version.query();
            });
    };

    $scope.clear = function () {
        $scope.version = {create_date: null, version: null, id: null};
    };
});
