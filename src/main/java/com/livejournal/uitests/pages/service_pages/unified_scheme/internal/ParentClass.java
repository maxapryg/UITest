/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livejournal.uitests.pages.service_pages.unified_scheme.internal;

/**
 *
 * @author s.savinykh
 */
public abstract class ParentClass {
     
     public void m1() {
         getInnerParentClass().m1();
     }
     
     protected abstract InnerParentClass getInnerParentClass();
     
}
