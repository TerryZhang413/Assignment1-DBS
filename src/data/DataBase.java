package data;

public class DataBase {

	    private double _id;
	    private String REGISTER_NAME;
	    private String BN_NAME;
	    private String BN_STATUS;
	    private String BN_REG_DT;
	    private String BN_CANCEL_DT;
	    private String BN_RENEW_DT;
	    private double BN_STATE_NUM;
	    private String BN_STATE_OF_REG;
	    private double BN_ABN;
	    
	    public DataBase(double _id,String REGISTER_NAME,String BN_NAME, String BN_STATUS,String BN_REG_DT,String BN_CANCEL_DT, String BN_RENEW_DT,double BN_STATE_NUM,String BN_STATE_OF_REG,double BN_ABN)
	    {
	        this.set_id(_id);
	        this.setREGISTER_NAME(REGISTER_NAME);
	        this.setBN_NAME(BN_NAME);
	        this.setBN_STATUS(BN_STATUS);
	        this.setBN_REG_DT(BN_REG_DT);
	        this.setBN_CANCEL_DT(BN_CANCEL_DT);
	        this.setBN_RENEW_DT(BN_RENEW_DT);
	        this.setBN_STATE_NUM(BN_STATE_NUM);
	        this.setBN_STATE_OF_REG(BN_STATE_OF_REG);
	        this.setBN_ABN(BN_ABN);
	    }


		public String getREGISTER_NAME() {
			return REGISTER_NAME;
		}

		public void setREGISTER_NAME(String rEGISTER_NAME) {
			REGISTER_NAME = rEGISTER_NAME;
		}

		public String getBN_NAME() {
			return BN_NAME;
		}

		public void setBN_NAME(String bN_NAME) {
			BN_NAME = bN_NAME;
		}

		public String getBN_STATUS() {
			return BN_STATUS;
		}

		public void setBN_STATUS(String bN_STATUS) {
			BN_STATUS = bN_STATUS;
		}

		public String getBN_REG_DT() {
			return BN_REG_DT;
		}

		public void setBN_REG_DT(String bN_REG_DT) {
			BN_REG_DT = bN_REG_DT;
		}

		public String getBN_CANCEL_DT() {
			return BN_CANCEL_DT;
		}

		public void setBN_CANCEL_DT(String bN_CANCEL_DT) {
			BN_CANCEL_DT = bN_CANCEL_DT;
		}

		public String getBN_RENEW_DT() {
			return BN_RENEW_DT;
		}

		public void setBN_RENEW_DT(String bN_RENEW_DT) {
			BN_RENEW_DT = bN_RENEW_DT;
		}


		public String getBN_STATE_OF_REG() {
			return BN_STATE_OF_REG;
		}

		public void setBN_STATE_OF_REG(String bN_STATE_OF_REG) {
			BN_STATE_OF_REG = bN_STATE_OF_REG;
		}




		public double get_id() {
			return _id;
		}




		public void set_id(double _id) {
			this._id = _id;
		}




		public double getBN_STATE_NUM() {
			return BN_STATE_NUM;
		}




		public void setBN_STATE_NUM(double bN_STATE_NUM) {
			BN_STATE_NUM = bN_STATE_NUM;
		}




		public double getBN_ABN() {
			return BN_ABN;
		}




		public void setBN_ABN(double bN_ABN) {
			BN_ABN = bN_ABN;
		}


}
