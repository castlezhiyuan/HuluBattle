/**
 *@author: DZ1933036_zhiyuan
 *@classname: filewrapper_is_Directory_URLResourceValues_isDirectory
 *@apiSignature:  FileWrapper$ var isDirectory ->Bool?;URLResourceValues$var isDirectory: ->Bool?
 *@description: This property contains a boolean value indicating whether the file wrapper is a directory file wrapper;True for directories.
 *Map:java.io.fileFile$ public boolean isDirectory()
 */
import Foundation
class filewrapper_isDirectory_URLResourceValues_isDirectory{
    /**
     * input: 0
     * class0: FileWrapper$class0
     * class1:URLResourceValues$class1
     * output: 2
     * ret0: ret0 = true
     * ret1: ret1 = true
     */
    static func isDirectory0(){
        print(">>>>>>>")
        guard let url0 = URL.init(string: "file:///Users/castle/Documents") else { return }
        var keys = Set<URLResourceKey>()
             keys.insert(URLResourceKey.isDirectoryKey)
        do{
        let filewrapper = try FileWrapper.init(url:url0)
        let ret0 = filewrapper.isDirectory
        let URLResourceValues = try url0.resourceValues(forKeys: keys)
        let ret1 = URLResourceValues.isDirectory
        assert(ret0 == true)
        print(ret0)
        assert(ret1 == true)
        print(ret1)
        }
        catch{print("error")}
       
    }
    static func isDirectory1(){
        /**
        * input: 0
        * class0: FileWrapper$class0
        * class1:URLResourceValues$class1
        * output: 2
        * ret0: ret0 = false
        * ret1: ret1 = false
        */
        print(">>>>>>>")
        guard let url0 = URL.init(string: "file:///Users/castle/Documents/1.jpeg") else { return }
        var keys = Set<URLResourceKey>()
             keys.insert(URLResourceKey.isDirectoryKey)
        do{
        let filewrapper = try FileWrapper.init(url:url0)
        let ret0 = filewrapper.isDirectory
        let URLResourceValues = try url0.resourceValues(forKeys: keys)
        let ret1 = URLResourceValues.isDirectory
        assert(ret0 == false)
        print(ret0)
        assert(ret1 == false)
        print(ret1)
        }
        catch{print("error")}
    }
}

