package com.estudy.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacebookResponseMapper {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Picture {

        public class Data {
            private String url;

            public Data() {
            }

            public Data(String url) {
                this.url = url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "Data [url=" + url + "]";
            }

        }

        private Data data;

    }

    private String email;
    private Picture picture;
    private String id;
    private String first_name;
    private String last_name;
}
