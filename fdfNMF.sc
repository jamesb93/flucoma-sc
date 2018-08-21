// adds an instance method to the Buffer class
+ Buffer {
	fdNMF { arg dstBuf, rank = 1, iterations = 100, fftSize = 2048, windowSize = 2048, hopSize = 512, action;
		var resp;

		if(bufnum.isNil) { Error("Cannot call % on a % that has been freed".format(thisMethod.name, this.class.name)).throw };

		// responder idea stolen from getToFloatArray
		resp = OSCFunc({ arg msg;
			if(msg[1]== '/b_gen' && msg[2]== bufnum, {
				resp.clear;
				action.value(bufnum);
			});
		}, '/done', server.addr);

		server.listSendMsg([\b_gen, bufnum, "BufNMF", dstBuf.bufnum, rank, iterations, fftSize, windowSize, hopSize])
	}
}
