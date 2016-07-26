package com.intentpumin.lsy.intentpumin.tools.mRemark;

/**
 * Created by yang on 2016/7/19.
 */
public class remark_list {

    /**
     * res : 1
     * msg :
     * data : {"date":"2016-07-01","area_id":"47875310","area_name":"上海","loct_id":"47875310","loct_name":"上海市安远路518号","eqpt_id":"FA0101001","eqpt_name":"1#烟感探头","axis_x":"","axis_y":"","axis_z":"","task_id":"FA0101001","task_name":"接线是否松动","task_comment":"接线是否松动","pmt_id":"S9001","pmt_name":"测试维护一","smt_id":"S9002","smt_name":"测试维护二","mt_id":"S9001","mt_name":"","exec_time":"2016-07-21 09:59:57","finished":"Y","spot_x":"121.437415","spot_y":"31.23703","spot_z":"","uploaded":"","remark":"yy"}
     */

    private int res;
    private String msg;
    /**
     * date : 2016-07-01
     * area_id : 47875310
     * area_name : 上海
     * loct_id : 47875310
     * loct_name : 上海市安远路518号
     * eqpt_id : FA0101001
     * eqpt_name : 1#烟感探头
     * axis_x :
     * axis_y :
     * axis_z :
     * task_id : FA0101001
     * task_name : 接线是否松动
     * task_comment : 接线是否松动
     * pmt_id : S9001
     * pmt_name : 测试维护一
     * smt_id : S9002
     * smt_name : 测试维护二
     * mt_id : S9001
     * mt_name :
     * exec_time : 2016-07-21 09:59:57
     * finished : Y
     * spot_x : 121.437415
     * spot_y : 31.23703
     * spot_z :
     * uploaded :
     * remark : yy
     */

    private DataBean data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String date;
        private String area_id;
        private String area_name;
        private String loct_id;
        private String loct_name;
        private String eqpt_id;
        private String eqpt_name;
        private String axis_x;
        private String axis_y;
        private String axis_z;
        private String task_id;
        private String task_name;
        private String task_comment;
        private String pmt_id;
        private String pmt_name;
        private String smt_id;
        private String smt_name;
        private String mt_id;
        private String mt_name;
        private String exec_time;
        private String finished;
        private String spot_x;
        private String spot_y;
        private String spot_z;
        private String uploaded;
        private String remark;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getLoct_id() {
            return loct_id;
        }

        public void setLoct_id(String loct_id) {
            this.loct_id = loct_id;
        }

        public String getLoct_name() {
            return loct_name;
        }

        public void setLoct_name(String loct_name) {
            this.loct_name = loct_name;
        }

        public String getEqpt_id() {
            return eqpt_id;
        }

        public void setEqpt_id(String eqpt_id) {
            this.eqpt_id = eqpt_id;
        }

        public String getEqpt_name() {
            return eqpt_name;
        }

        public void setEqpt_name(String eqpt_name) {
            this.eqpt_name = eqpt_name;
        }

        public String getAxis_x() {
            return axis_x;
        }

        public void setAxis_x(String axis_x) {
            this.axis_x = axis_x;
        }

        public String getAxis_y() {
            return axis_y;
        }

        public void setAxis_y(String axis_y) {
            this.axis_y = axis_y;
        }

        public String getAxis_z() {
            return axis_z;
        }

        public void setAxis_z(String axis_z) {
            this.axis_z = axis_z;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getTask_name() {
            return task_name;
        }

        public void setTask_name(String task_name) {
            this.task_name = task_name;
        }

        public String getTask_comment() {
            return task_comment;
        }

        public void setTask_comment(String task_comment) {
            this.task_comment = task_comment;
        }

        public String getPmt_id() {
            return pmt_id;
        }

        public void setPmt_id(String pmt_id) {
            this.pmt_id = pmt_id;
        }

        public String getPmt_name() {
            return pmt_name;
        }

        public void setPmt_name(String pmt_name) {
            this.pmt_name = pmt_name;
        }

        public String getSmt_id() {
            return smt_id;
        }

        public void setSmt_id(String smt_id) {
            this.smt_id = smt_id;
        }

        public String getSmt_name() {
            return smt_name;
        }

        public void setSmt_name(String smt_name) {
            this.smt_name = smt_name;
        }

        public String getMt_id() {
            return mt_id;
        }

        public void setMt_id(String mt_id) {
            this.mt_id = mt_id;
        }

        public String getMt_name() {
            return mt_name;
        }

        public void setMt_name(String mt_name) {
            this.mt_name = mt_name;
        }

        public String getExec_time() {
            return exec_time;
        }

        public void setExec_time(String exec_time) {
            this.exec_time = exec_time;
        }

        public String getFinished() {
            return finished;
        }

        public void setFinished(String finished) {
            this.finished = finished;
        }

        public String getSpot_x() {
            return spot_x;
        }

        public void setSpot_x(String spot_x) {
            this.spot_x = spot_x;
        }

        public String getSpot_y() {
            return spot_y;
        }

        public void setSpot_y(String spot_y) {
            this.spot_y = spot_y;
        }

        public String getSpot_z() {
            return spot_z;
        }

        public void setSpot_z(String spot_z) {
            this.spot_z = spot_z;
        }

        public String getUploaded() {
            return uploaded;
        }

        public void setUploaded(String uploaded) {
            this.uploaded = uploaded;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
