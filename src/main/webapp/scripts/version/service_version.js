'use strict';

mygesthandJhipsterApp.factory('Version', function ($resource) {
    return $resource('app/rest/versions/:id', {}, {
        'query': {method: 'GET', isArray: true},
        'get': {method: 'GET'}
    });
});
