//
//  maptable.swift
//  testcase
//
//  Created by 支原 on 2019/11/13.
//  Copyright © 2019 支原. All rights reserved.
//

import Foundation
class NSMapTable_setObject_ObjectType_KeyTpye{
    static func setObject0()
    {
        print(">>>>>>")
        let mapTable = NSMapTable<NSString,NSString>(keyOptions: .strongMemory, valueOptions: .weakMemory)
        mapTable.setObject("100", forKey: "first")
        print(mapTable.object(forKey: "first"))
    
    }
    static func setObject1()
       {
           print(">>>>>>")
           let mapTable = NSMapTable<NSString,NSString>(keyOptions: .strongMemory, valueOptions: .weakMemory)
           mapTable.setObject("100", forKey: "first")
           mapTable.setObject("200", forKey: "first")
           print(mapTable.object(forKey: "first"))
       }
   
}


