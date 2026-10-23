package com.capstoneProject.graps.entity;

public enum RouteType {
      uni_assist("Uni-assist"),
      direct("Direct");

      private final String label;

      RouteType(String label){
            this.label = label;
      }
      public String getLabel(){
            return label;
      }
}
