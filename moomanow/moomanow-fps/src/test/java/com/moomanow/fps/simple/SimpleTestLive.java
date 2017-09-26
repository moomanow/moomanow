package com.moomanow.fps.simple;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.moomanow.fps.backbone.BackBone;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
	    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = {
        "classpath:spring.xml",
        "classpath:prj-simpleProcess.xml"})
public class SimpleTestLive {

	 @Autowired
	 private BackBone backBone;
	@Test
	public void test() {
//		BackBone backBone = new BackBone();
//		backBone.setBrainService(new BrainServiceImpl());
//		backBone.setDynamicBeanService(new DynamicBeanServiceImpl());
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleInterFaceDataInput simpleInterFaceData = backBone.buildBean("simple", map, SimpleInterFaceDataInput.class);
//		INeuronResult<SimpleInterFaceDataOut> iNeuronResult = backBone.execute("simple", map,SimpleInterFaceDataOut.class);

		// Class<?> c = SimpleProcessNeuron.class.getSuperclass();
		// out.format("Class:%n %s%n%n", c.getCanonicalName());
		// out.format("Modifiers:%n %s%n%n",
		// Modifier.toString(c.getModifiers()));
		//
		// out.format("Type Parameters:%n");
		// TypeVariable[] tv = c.getTypeParameters();
		// if (tv.length != 0) {
		// out.format(" ");
		// for (TypeVariable t : tv)
		// out.format("%s ", t.getName());
		// out.format("%n%n");
		// } else {
		// out.format(" -- No Type Parameters --%n%n");
		// }
		//
		// out.format("Implemented Interfaces:%n");
		// Type[] intfs = c.getGenericInterfaces();
		// if (intfs.length != 0) {
		// for (Type intf : intfs)
		// out.format(" %s%n", intf.toString());
		// out.format("%n");
		// } else {
		// out.format(" -- No Implemented Interfaces --%n%n");
		// }
		//
		// out.format("Inheritance Path:%n");
		// List<Class> l = new ArrayList<Class>();
		// printAncestor(c, l);
		// if (l.size() != 0) {
		// for (Class<?> cl : l)
		// out.format(" %s%n", cl.getCanonicalName());
		// out.format("%n");
		// } else {
		// out.format(" -- No Super Classes --%n%n");
		// }
		//
		// out.format("Annotations:%n");
		// Annotation[] ann = c.getAnnotations();
		// if (ann.length != 0) {
		// for (Annotation a : ann)
		// out.format(" %s%n", a.toString());
		// out.format("%n");
		// } else {
		// out.format(" -- No Annotations --%n%n");
		// }
	}

//	private static void printAncestor(Class<?> c, List<Class> l) {
//		Class<?> ancestor = c.getSuperclass();
//		if (ancestor != null) {
//			l.add(ancestor);
//			printAncestor(ancestor, l);
//		}
//	}
}
