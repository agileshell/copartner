require.config({
    baseUrl: '/assets/',
    paths: {
        "jquery": "js/jquery",
        "bootstrap": "js/bootstrap",
        "validate": "js/jquery.validate.min",
        "uploadify": "js/uploadify/jquery.uploadify.min"
    },
    shim : {
        bootstrap : {
            deps : [ 'jquery' ],
            exports : 'bootstrap'
        },
        uploadify : {
            deps : [ 'jquery' ]
        },
        common : {
            deps : [ 'jquery' ]
        }
    }
});