package com.example.aboutview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceModel {
    /**
     * part_id : 57561268
     * reference_number1 : 010
     * reference_number2 : 01
     * revision_number : 10
     * part_number : 7F613-45110
     * part_name : ﾕﾆﾊﾞ-ｻﾙｼﾞﾖｲﾝﾄ,ｱﾂｼ
     * quantity : 1
     * compatibility_type :
     * serial_number :
     * remarks :
     * weight : 5.7
     * weight_unit : Kg
     * frt :
     * illustrations : [{"illustration_id":192347,"positions":[{"start_x":1885,"end_x":1982,"start_y":300,"end_y":349}]}]
     * service_bulletins : []
     * memos : [{"memo_id":310,"items":[{"language":"ja","title":"test","content":"test","file_id":16,"file_name":"添付した画像.jpg","file_size":10972}],"modified_by":"all","start_date":"2019-09-17T11:45:22.864+09:00","type":1,"is_editable":false,"open_ranges":{"private":{"selected":false},"role":{"selected":false,"roles":null},"group":{"selected":false,"groups":null},"type":""}}]
     * is_memo_addable : true
     * is_sb_addable : false
     * maintenances : []
     * selected_maintenance_name :
     * selected_maintenance_id : 0
     * recommended_part_ids : []
     * fui : {"section_name":"","services":null,"capacity":0,"capacity_unit_name":"","related_figs":null}
     */

    private int part_id;
    private String reference_number1;
    private String reference_number2;
    private String revision_number;
    private String part_number;
    private String part_name;
    private int quantity;
    private String compatibility_type;
    private String serial_number;
    private String remarks;
    private double weight;
    private String weight_unit;
    private String frt;
    private boolean is_memo_addable;
    private boolean is_sb_addable;
    private String selected_maintenance_name;
    private int selected_maintenance_id;
    private FuiBean fui;
    private List<IllustrationsBean> illustrations;
    private List<?> service_bulletins;
    private List<MemosBean> memos;
    private List<?> maintenances;
    private List<?> recommended_part_ids;

    public int getPart_id() {
        return part_id;
    }

    public void setPart_id(int part_id) {
        this.part_id = part_id;
    }

    public String getReference_number1() {
        return reference_number1;
    }

    public void setReference_number1(String reference_number1) {
        this.reference_number1 = reference_number1;
    }

    public String getReference_number2() {
        return reference_number2;
    }

    public void setReference_number2(String reference_number2) {
        this.reference_number2 = reference_number2;
    }

    public String getRevision_number() {
        return revision_number;
    }

    public void setRevision_number(String revision_number) {
        this.revision_number = revision_number;
    }

    public String getPart_number() {
        return part_number;
    }

    public void setPart_number(String part_number) {
        this.part_number = part_number;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCompatibility_type() {
        return compatibility_type;
    }

    public void setCompatibility_type(String compatibility_type) {
        this.compatibility_type = compatibility_type;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }

    public String getFrt() {
        return frt;
    }

    public void setFrt(String frt) {
        this.frt = frt;
    }

    public boolean isIs_memo_addable() {
        return is_memo_addable;
    }

    public void setIs_memo_addable(boolean is_memo_addable) {
        this.is_memo_addable = is_memo_addable;
    }

    public boolean isIs_sb_addable() {
        return is_sb_addable;
    }

    public void setIs_sb_addable(boolean is_sb_addable) {
        this.is_sb_addable = is_sb_addable;
    }

    public String getSelected_maintenance_name() {
        return selected_maintenance_name;
    }

    public void setSelected_maintenance_name(String selected_maintenance_name) {
        this.selected_maintenance_name = selected_maintenance_name;
    }

    public int getSelected_maintenance_id() {
        return selected_maintenance_id;
    }

    public void setSelected_maintenance_id(int selected_maintenance_id) {
        this.selected_maintenance_id = selected_maintenance_id;
    }

    public FuiBean getFui() {
        return fui;
    }

    public void setFui(FuiBean fui) {
        this.fui = fui;
    }

    public List<IllustrationsBean> getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(List<IllustrationsBean> illustrations) {
        this.illustrations = illustrations;
    }

    public List<?> getService_bulletins() {
        return service_bulletins;
    }

    public void setService_bulletins(List<?> service_bulletins) {
        this.service_bulletins = service_bulletins;
    }

    public List<MemosBean> getMemos() {
        return memos;
    }

    public void setMemos(List<MemosBean> memos) {
        this.memos = memos;
    }

    public List<?> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<?> maintenances) {
        this.maintenances = maintenances;
    }

    public List<?> getRecommended_part_ids() {
        return recommended_part_ids;
    }

    public void setRecommended_part_ids(List<?> recommended_part_ids) {
        this.recommended_part_ids = recommended_part_ids;
    }

    public static class FuiBean {
        /**
         * section_name :
         * services : null
         * capacity : 0
         * capacity_unit_name :
         * related_figs : null
         */

        private String section_name;
        private Object services;
        private int capacity;
        private String capacity_unit_name;
        private Object related_figs;

        public String getSection_name() {
            return section_name;
        }

        public void setSection_name(String section_name) {
            this.section_name = section_name;
        }

        public Object getServices() {
            return services;
        }

        public void setServices(Object services) {
            this.services = services;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public String getCapacity_unit_name() {
            return capacity_unit_name;
        }

        public void setCapacity_unit_name(String capacity_unit_name) {
            this.capacity_unit_name = capacity_unit_name;
        }

        public Object getRelated_figs() {
            return related_figs;
        }

        public void setRelated_figs(Object related_figs) {
            this.related_figs = related_figs;
        }
    }

    public static class IllustrationsBean {
        /**
         * illustration_id : 192347
         * positions : [{"start_x":1885,"end_x":1982,"start_y":300,"end_y":349}]
         */

        private int illustration_id;
        private List<PositionsBean> positions;

        public int getIllustration_id() {
            return illustration_id;
        }

        public void setIllustration_id(int illustration_id) {
            this.illustration_id = illustration_id;
        }

        public List<PositionsBean> getPositions() {
            return positions;
        }

        public void setPositions(List<PositionsBean> positions) {
            this.positions = positions;
        }

        public static class PositionsBean {
            /**
             * start_x : 1885
             * end_x : 1982
             * start_y : 300
             * end_y : 349
             */

            private int start_x;
            private int end_x;
            private int start_y;
            private int end_y;

            public int getStart_x() {
                return start_x;
            }

            public void setStart_x(int start_x) {
                this.start_x = start_x;
            }

            public int getEnd_x() {
                return end_x;
            }

            public void setEnd_x(int end_x) {
                this.end_x = end_x;
            }

            public int getStart_y() {
                return start_y;
            }

            public void setStart_y(int start_y) {
                this.start_y = start_y;
            }

            public int getEnd_y() {
                return end_y;
            }

            public void setEnd_y(int end_y) {
                this.end_y = end_y;
            }
        }
    }

    public static class MemosBean {
        /**
         * memo_id : 310
         * items : [{"language":"ja","title":"test","content":"test","file_id":16,"file_name":"添付した画像.jpg","file_size":10972}]
         * modified_by : all
         * start_date : 2019-09-17T11:45:22.864+09:00
         * type : 1
         * is_editable : false
         * open_ranges : {"private":{"selected":false},"role":{"selected":false,"roles":null},"group":{"selected":false,"groups":null},"type":""}
         */

        private int memo_id;
        private String modified_by;
        private String start_date;
        private int type;
        private boolean is_editable;
        private OpenRangesBean open_ranges;
        private List<ItemsBean> items;

        public int getMemo_id() {
            return memo_id;
        }

        public void setMemo_id(int memo_id) {
            this.memo_id = memo_id;
        }

        public String getModified_by() {
            return modified_by;
        }

        public void setModified_by(String modified_by) {
            this.modified_by = modified_by;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isIs_editable() {
            return is_editable;
        }

        public void setIs_editable(boolean is_editable) {
            this.is_editable = is_editable;
        }

        public OpenRangesBean getOpen_ranges() {
            return open_ranges;
        }

        public void setOpen_ranges(OpenRangesBean open_ranges) {
            this.open_ranges = open_ranges;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class OpenRangesBean {
            /**
             * private : {"selected":false}
             * role : {"selected":false,"roles":null}
             * group : {"selected":false,"groups":null}
             * type :
             */

            @SerializedName("private")
            private PrivateBean privateX;
            private RoleBean role;
            private GroupBean group;
            private String type;

            public PrivateBean getPrivateX() {
                return privateX;
            }

            public void setPrivateX(PrivateBean privateX) {
                this.privateX = privateX;
            }

            public RoleBean getRole() {
                return role;
            }

            public void setRole(RoleBean role) {
                this.role = role;
            }

            public GroupBean getGroup() {
                return group;
            }

            public void setGroup(GroupBean group) {
                this.group = group;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class PrivateBean {
                /**
                 * selected : false
                 */

                private boolean selected;

                public boolean isSelected() {
                    return selected;
                }

                public void setSelected(boolean selected) {
                    this.selected = selected;
                }
            }

            public static class RoleBean {
                /**
                 * selected : false
                 * roles : null
                 */

                private boolean selected;
                private Object roles;

                public boolean isSelected() {
                    return selected;
                }

                public void setSelected(boolean selected) {
                    this.selected = selected;
                }

                public Object getRoles() {
                    return roles;
                }

                public void setRoles(Object roles) {
                    this.roles = roles;
                }
            }

            public static class GroupBean {
                /**
                 * selected : false
                 * groups : null
                 */

                private boolean selected;
                private Object groups;

                public boolean isSelected() {
                    return selected;
                }

                public void setSelected(boolean selected) {
                    this.selected = selected;
                }

                public Object getGroups() {
                    return groups;
                }

                public void setGroups(Object groups) {
                    this.groups = groups;
                }
            }
        }

        public static class ItemsBean {
            /**
             * language : ja
             * title : test
             * content : test
             * file_id : 16
             * file_name : 添付した画像.jpg
             * file_size : 10972
             */

            private String language;
            private String title;
            private String content;
            private int file_id;
            private String file_name;
            private int file_size;

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getFile_id() {
                return file_id;
            }

            public void setFile_id(int file_id) {
                this.file_id = file_id;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public int getFile_size() {
                return file_size;
            }

            public void setFile_size(int file_size) {
                this.file_size = file_size;
            }
        }
    }
}
