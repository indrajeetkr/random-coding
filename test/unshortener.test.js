var unshortener = require('../lib/unshortener')
  , assert = require('assert');

var urllib = require('url');


var unshort = unshortener.Unshorteners;

module.exports = {
    'expand_string': function (beforeExit) {
	var fired = false;

        unshortener.expand('http://is.gd/E27w2x', function (url) {
            assert.equal(url.href,
			 'http://holykaw.alltop.com/why-sleep-may-be-the-best-tool-for-tough-deci?tu3=1');
	    fired = true;
        });

	beforeExit(function () {
	    assert.equal(fired, true);
	});
    },

    'expand_object': function (beforeExit) {
        var fired = false;
        unshortener.expand(urllib.parse('http://t.co/rWP6BP3'),
                           function (url) {
                               assert.equal(url.href,
					    'http://www.facebook.com/mybrucebruce');
			       fired = true;
                           });

	beforeExit(function () {
	    assert.equal(fired, true);
	});
    },

    'expand_bitly': function (beforeExit) {
	var fired = false;
	unshort.bitly(urllib.parse('http://bit.ly/lyQusq'),
		      function (url) {
			  assert.equal(url.href,
				       'http://www.crunchgear.com/2011/05/18/review-two-speck-ipad-2-cases/?utm_source=twitterfeed&utm_medium=twitter');
			  fired = true;
		      });

	beforeExit(function () {
	    assert.equal(fired, true);
	});
    }
};
