package com.example.pc.imitationliangcang.bean.magazinefragment;

import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/7/10.
 */

public class MagazineListBean {

    private MetaBean meta;
    private int version;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * status : 0
         * server_time : 2017-07-10 18:16:43
         * account_id : 0
         * cost : 0.18586897850037
         * errdata : null
         * errmsg :
         */

        private int status;
        private String server_time;
        private int account_id;
        private double cost;
        private Object errdata;
        private String errmsg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getServer_time() {
            return server_time;
        }

        public void setServer_time(String server_time) {
            this.server_time = server_time;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public Object getErrdata() {
            return errdata;
        }

        public void setErrdata(Object errdata) {
            this.errdata = errdata;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }

    public static class DataBean{
        private boolean has_more;
        private int num_items;
        private ItemsBean items;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public int getNum_items() {
            return num_items;
        }

        public void setNum_items(int num_items) {
            this.num_items = num_items;
        }

        public ItemsBean getItems() {
            return items;
        }

        public void setItems(ItemsBean items) {
            this.items = items;
        }

        public static class ItemsBean{

            private Map<String,List<Bean>> infos;

            private List<String> keys;

            public Map<String, List<Bean>> getInfos() {
                return infos;
            }

            public void setInfos(Map<String, List<Bean>> infos) {
                this.infos = infos;
            }

            public List<String> getKeys() {
                return keys;
            }

            public void setKeys(List<String> keys) {
                this.keys = keys;
            }

            public static class Bean{
                /**
                 //                 * taid : 2056
                 //                 * topic_name : 在社交媒体上火爆的10对在路上的情侣
                 //                 * cat_id : 11
                 //                 * author_id : 1
                 //                 * topic_url : http://www.iliangcang.com/i/topicapp/201707072532
                 //                 * access_url : http://www.iliangcang.com/i/topicapp/201707072532
                 //                 * cover_img : http://imgs-qn.iliangcang.com/ware/appimg/topic/cover/2056_.jpg?_t=1499443683
                 //                 * cover_img_new : http://imgs-qn.iliangcang.com/ware/appimg/topic/cover/2056_.jpg?_t=1499443683
                 //                 * hit_number : 462
                 //                 * addtime : 2017-07-08 23:13:34
                 //                 * content :
                 //                 * nav_title : 良仓杂志
                 //                 * author_name : 良仓
                 //                 * cat_name : 文化
                 //                 */

                private String taid;
                private String topic_name;
                private String cat_id;
                private String author_id;
                private String topic_url;
                private String access_url;
                private String cover_img;
                private String cover_img_new;
                private int hit_number;
                private String addtime;
                private String content;
                private String nav_title;
                private String author_name;
                private String cat_name;

                public String getTaid() {
                    return taid;
                }

                public void setTaid(String taid) {
                    this.taid = taid;
                }

                public String getTopic_name() {
                    return topic_name;
                }

                public void setTopic_name(String topic_name) {
                    this.topic_name = topic_name;
                }

                public String getCat_id() {
                    return cat_id;
                }

                public void setCat_id(String cat_id) {
                    this.cat_id = cat_id;
                }

                public String getAuthor_id() {
                    return author_id;
                }

                public void setAuthor_id(String author_id) {
                    this.author_id = author_id;
                }

                public String getTopic_url() {
                    return topic_url;
                }

                public void setTopic_url(String topic_url) {
                    this.topic_url = topic_url;
                }

                public String getAccess_url() {
                    return access_url;
                }

                public void setAccess_url(String access_url) {
                    this.access_url = access_url;
                }

                public String getCover_img() {
                    return cover_img;
                }

                public void setCover_img(String cover_img) {
                    this.cover_img = cover_img;
                }

                public String getCover_img_new() {
                    return cover_img_new;
                }

                public void setCover_img_new(String cover_img_new) {
                    this.cover_img_new = cover_img_new;
                }

                public int getHit_number() {
                    return hit_number;
                }

                public void setHit_number(int hit_number) {
                    this.hit_number = hit_number;
                }

                public String getAddtime() {
                    return addtime;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getNav_title() {
                    return nav_title;
                }

                public void setNav_title(String nav_title) {
                    this.nav_title = nav_title;
                }

                public String getAuthor_name() {
                    return author_name;
                }

                public void setAuthor_name(String author_name) {
                    this.author_name = author_name;
                }

                public String getCat_name() {
                    return cat_name;
                }

                public void setCat_name(String cat_name) {
                    this.cat_name = cat_name;
                }
            }
        }
    }
}
