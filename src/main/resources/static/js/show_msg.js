this.showErrMsg = function(errMsg) {
	if (errMsg == "null" || errMsg == "") {
		return;
	}

	if (this.myToast != null) {
		$().toastmessage('removeToast', this.myToast);
	}

	this.myToast = $().toastmessage('showToast', {
	    text     : errMsg,
	    stayTime :  5000,
	    sticky   : false,
	    type     : 'error',
	    position : 'top-center'
	});
};

this.showNoticeMsg = function(noticeMsg) {
	if (noticeMsg == "null" || noticeMsg == "") {
		return;
	}

	if (this.myToast != null) {
		$().toastmessage('removeToast', this.myToast);
	}

	this.myToast = $().toastmessage('showToast', {
	    text     : noticeMsg,
	    stayTime:  5000,
	    sticky   : false,
	    type     : 'notice',
	    position : 'top-center'
	});
};

this.showWarnMsg = function(warnMsg) {
	if (warnMsg == "null" || warnMsg == "") {
		return;
	}

	if (this.myToast != null) {
		$().toastmessage('removeToast', this.myToast);
	}

	this.myToast = $().toastmessage('showToast', {
	    text     : warnMsg,
	    stayTime:  5000,
	    sticky   : false,
	    type     : 'warning',
	    position : 'top-center'
	});
};

this.showSuccessMsg = function(successMsg) {
	if (successMsg == "null" || successMsg == "") {
		return;
	}

	if (this.myToast != null) {
		$().toastmessage('removeToast', this.myToast);
	}

	this.myToast = $().toastmessage('showToast', {
	    text     : successMsg,
	    stayTime :  5000,
	    sticky   : false, 
	    type     : 'success',
        dataType : 'json',
        position : 'top-center',
        success: function(data) {
            successFun(data);
        },
        error: function() {
        }
    });
}