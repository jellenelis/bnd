import aQute.bnd.build.*;
import aQute.bnd.build.model.*;
import aQute.bnd.build.model.clauses.*;
import aQute.bnd.osgi.*;
import aQute.bnd.properties.*;
import aQute.lib.io.*;
import java.util.jar.*;

println "basedir ${basedir}"

// Check the bndrun file exist!
File bndrunFile = new File(basedir, 'test.bndrun')
assert bndrunFile.isFile()

// Load the BndEditModel of the bndrun file so we can inspect the result
Processor processor = new Processor()
processor.setProperties(bndrunFile)
BndEditModel bem = new BndEditModel(Workspace.createStandaloneWorkspace(processor, bndrunFile.toURI()))
Document doc = new Document(IO.collect(bndrunFile))
bem.loadFrom(doc)

// Get the -runbundles.
def bemRunBundles = bem.getRunBundles()
assert bemRunBundles
assert bemRunBundles.size() == 1

StringBuilder sb = new StringBuilder()
bemRunBundles.get(0).formatTo(sb)
assert sb.toString() == "org.apache.felix.eventadmin;version='[1.4.6,1.4.7)'"

