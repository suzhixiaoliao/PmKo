package com.intentpumin.lsy.intentpumin.tools.repairlist;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/8/9.
 */
public class Repair_item implements Serializable{
        private String repair_id;
        private String repair_priv;
        private String repair_sent_user_id;
        private String repair_sent_user_name;
        private String repair_sent_user_phoneno;
        private String process_flag;
        private String alm_sent_datetime;
        private String alm_comment;
        private String eqpt_id;
        private String eqpt_name;
        private Object area_id;
        private String area_name;
        private Object loct_id;
        private String loct_name;
        private String repair_get_user_id;
        private String repair_get_user_name;
        private String repair_get_user_phoneno;
        private String alm_get_datetime;
        private String repair_get_user_leader_id;
        private String repair_get_user_leader_name;
        private String repair_get_user_leader_phoneno;
        private String eqpt_mf_name;
        private String eqpt_mf_phoneno;
        private String onspot_datetime;
        private String repair_comment;
        private String done_datetime;
        private String close_datetime;
        private String repair_close_user_id;
        private String repair_close_user_name;
        private String repair_close_user_phoneno;
        private String attachments;
        private String _attachments;
        private String remark;
        /**
         * attachment_id : 72CEE209-7B9B-AEB6-E624-C5AF2C7D4A9E
         * attachment_hash : 2c4f93c6d61a0a511df34fccde164ea3
         * userid : S9003
         * file_module : repair
         * file_name : IMG_14704571028599_1.png
         * file_path : uploads/repair/201608/06/
         * file_size : 903975
         * file_extension : image/png
         * image : 1
         * thumb :
         * used : 1
         * subject : Screenshot_2016-08-05-14-26-48.png
         * dateline : 1470457102
         * lastdate : 1470457102
         * ipaddr : 180.152.234.233
         * disabled : 0
         */
        public String getRepair_id() {
            return repair_id;
        }

        public void setRepair_id(String repair_id) {
            this.repair_id = repair_id;
        }

        public String getRepair_priv() {
            return repair_priv;
        }

        public void setRepair_priv(String repair_priv) {
            this.repair_priv = repair_priv;
        }

        public String getRepair_sent_user_id() {
            return repair_sent_user_id;
        }

        public void setRepair_sent_user_id(String repair_sent_user_id) {
            this.repair_sent_user_id = repair_sent_user_id;
        }

        public String getRepair_sent_user_name() {
            return repair_sent_user_name;
        }

        public void setRepair_sent_user_name(String repair_sent_user_name) {
            this.repair_sent_user_name = repair_sent_user_name;
        }

        public String getRepair_sent_user_phoneno() {
            return repair_sent_user_phoneno;
        }

        public void setRepair_sent_user_phoneno(String repair_sent_user_phoneno) {
            this.repair_sent_user_phoneno = repair_sent_user_phoneno;
        }

        public String getProcess_flag() {
            return process_flag;
        }

        public void setProcess_flag(String process_flag) {
            this.process_flag = process_flag;
        }

        public String getAlm_sent_datetime() {
            return alm_sent_datetime;
        }

        public void setAlm_sent_datetime(String alm_sent_datetime) {
            this.alm_sent_datetime = alm_sent_datetime;
        }

        public String getAlm_comment() {
            return alm_comment;
        }

        public void setAlm_comment(String alm_comment) {
            this.alm_comment = alm_comment;
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

        public Object getArea_id() {
            return area_id;
        }

        public void setArea_id(Object area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public Object getLoct_id() {
            return loct_id;
        }

        public void setLoct_id(Object loct_id) {
            this.loct_id = loct_id;
        }

        public String getLoct_name() {
            return loct_name;
        }

        public void setLoct_name(String loct_name) {
            this.loct_name = loct_name;
        }

        public String getRepair_get_user_id() {
            return repair_get_user_id;
        }

        public void setRepair_get_user_id(String repair_get_user_id) {
            this.repair_get_user_id = repair_get_user_id;
        }

        public String getRepair_get_user_name() {
            return repair_get_user_name;
        }

        public void setRepair_get_user_name(String repair_get_user_name) {
            this.repair_get_user_name = repair_get_user_name;
        }

        public String getRepair_get_user_phoneno() {
            return repair_get_user_phoneno;
        }

        public void setRepair_get_user_phoneno(String repair_get_user_phoneno) {
            this.repair_get_user_phoneno = repair_get_user_phoneno;
        }

        public String getAlm_get_datetime() {
            return alm_get_datetime;
        }

        public void setAlm_get_datetime(String alm_get_datetime) {
            this.alm_get_datetime = alm_get_datetime;
        }

        public String getRepair_get_user_leader_id() {
            return repair_get_user_leader_id;
        }

        public void setRepair_get_user_leader_id(String repair_get_user_leader_id) {
            this.repair_get_user_leader_id = repair_get_user_leader_id;
        }

        public String getRepair_get_user_leader_name() {
            return repair_get_user_leader_name;
        }

        public void setRepair_get_user_leader_name(String repair_get_user_leader_name) {
            this.repair_get_user_leader_name = repair_get_user_leader_name;
        }

        public String getRepair_get_user_leader_phoneno() {
            return repair_get_user_leader_phoneno;
        }

        public void setRepair_get_user_leader_phoneno(String repair_get_user_leader_phoneno) {
            this.repair_get_user_leader_phoneno = repair_get_user_leader_phoneno;
        }

        public String getEqpt_mf_name() {
            return eqpt_mf_name;
        }

        public void setEqpt_mf_name(String eqpt_mf_name) {
            this.eqpt_mf_name = eqpt_mf_name;
        }

        public String getEqpt_mf_phoneno() {
            return eqpt_mf_phoneno;
        }

        public void setEqpt_mf_phoneno(String eqpt_mf_phoneno) {
            this.eqpt_mf_phoneno = eqpt_mf_phoneno;
        }

        public String getOnspot_datetime() {
            return onspot_datetime;
        }

        public void setOnspot_datetime(String onspot_datetime) {
            this.onspot_datetime = onspot_datetime;
        }

        public String getRepair_comment() {
            return repair_comment;
        }

        public void setRepair_comment(String repair_comment) {
            this.repair_comment = repair_comment;
        }

        public String getDone_datetime() {
            return done_datetime;
        }

        public void setDone_datetime(String done_datetime) {
            this.done_datetime = done_datetime;
        }

        public String getClose_datetime() {
            return close_datetime;
        }

        public void setClose_datetime(String close_datetime) {
            this.close_datetime = close_datetime;
        }

        public String getRepair_close_user_id() {
            return repair_close_user_id;
        }

        public void setRepair_close_user_id(String repair_close_user_id) {
            this.repair_close_user_id = repair_close_user_id;
        }

        public String getRepair_close_user_name() {
            return repair_close_user_name;
        }

        public void setRepair_close_user_name(String repair_close_user_name) {
            this.repair_close_user_name = repair_close_user_name;
        }

        public String getRepair_close_user_phoneno() {
            return repair_close_user_phoneno;
        }

        public void setRepair_close_user_phoneno(String repair_close_user_phoneno) {
            this.repair_close_user_phoneno = repair_close_user_phoneno;
        }

        public String getAttachments() {
            return attachments;
        }

        public void setAttachments(String attachments) {
            this.attachments = attachments;
        }

        public String get_attachments() {
            return _attachments;
        }

        public void set_attachments(String _attachments) {
            this._attachments = _attachments;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }


