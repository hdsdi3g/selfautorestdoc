/*
 * This file is part of SelfAutoRESTDoc.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * Copyright (C) hdsdi3g for hd3g.tv 2019
 *
 */
package tv.hd3g.selfautorestdoc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import spoon.Launcher;

public class SelfAutoRESTDoc {

	private final ConcurrentHashMap<Class<?>, RESTController> restControllerAutoDocByClass;

	public SelfAutoRESTDoc() {
		restControllerAutoDocByClass = new ConcurrentHashMap<>();
	}

	public void registerClass(final Class<?> cl) {
		if (cl.isAnnotationPresent(RestController.class)) {
			restControllerAutoDocByClass.computeIfAbsent(cl, RESTController::new);
		}
	}

	public void writeToMD(final File mdFile) throws IOException {
		final var entryPoints = processAnalysis();

		try (final PrintWriter pw = new PrintWriter(mdFile)) {
			pw.println("# REST API");
			writeSummary(entryPoints, pw);
			entryPoints.forEach(ep -> writeContent(ep, pw));
			pw.println();
		}
	}

	private List<RESTEntryPoint> processAnalysis() {
		final var launcher = new Launcher();
		launcher.addInputResource("src/main/java");
		launcher.buildModel();
		final var model = launcher.getModel();

		return restControllerAutoDocByClass.values().stream()
		        .map(c -> c.processAnalysis(model))
		        .flatMap(controller -> controller.getMethods().stream()
		                .map(method -> new RESTEntryPoint(controller, method)))
		        .sorted((l, r) -> (l.getPaths() + l.getVerbs()).compareTo(r.getPaths() + r.getVerbs()))
		        .collect(Collectors.toUnmodifiableList());
	}

	private static String linkifyTitle(final String title) {
		return title.trim().replace(' ', '-').toLowerCase()
		        .replace("?", "")
		        .replace("!", "")
		        .replace(",", "")
		        .replace(";", "")
		        .replace(":", "")
		        .replace(".", "");
	}

	private void writeSummary(final List<RESTEntryPoint> entryPoints, final PrintWriter pw) {
		pw.println("## Summary");
		entryPoints.forEach(ep -> {
			pw.println();
			pw.print(" - [");
			pw.print(ep.getRequestNames() + " **" + ep.getVerbs() + "** " + ep.getPaths());
			pw.print("](#");
			pw.print(linkifyTitle(ep.getRequestNames() + " " + ep.getVerbs() + " " + ep.getPaths()));
			pw.print(")");
		});
		pw.println();
	}

	private void writeContent(final RESTEntryPoint ep, final PrintWriter pw) {
		pw.println();
		pw.println();
		pw.print("## ");
		final var rqname = ep.getRequestNames();
		if (rqname.isEmpty() == false) {
			pw.println(rqname);
		}
		pw.print("**" + ep.getVerbs() + "** ");
		pw.println(ep.getPaths());
		pw.println();

		ep.getMethodComments().forEach(c -> {
			pw.println(c);
			pw.println();
		});
		if (ep.getMethodComments().isEmpty()) {
			pw.println(ep.getMethodName());
			pw.println();
		}

		final var urlParameters = ep.getUrlParameters();
		if (urlParameters.isEmpty() == false) {
			pw.println("Parameters:");
			urlParameters.forEach((n, t) -> pw.println(" - **" + n + "** " + t));
			pw.println();
		}
		final var headers = ep.getHeaders();
		if (rqname.isEmpty() == false) {
			pw.print("Headers: ");
			pw.println(headers);
			pw.println();
		}

		ep.getDTORequest().ifPresent(r -> {
			pw.println("```javascript");
			pw.print("Request body data: ");
			final var consumes = ep.getConsumes();
			if (consumes.isBlank() == false) {
				pw.print("\"");
				pw.print(consumes);
				pw.print("\" ");
			}
			pw.println("{");
			r.forEach(pw::println);
			pw.println("}");
			pw.println("```");
			pw.println();
		});

		ep.getDTOResponse().ifPresent(r -> {
			pw.println("```javascript");
			pw.print("Response: ");
			final var produces = ep.getProduces();
			if (produces.isBlank() == false) {
				pw.print("\"");
				pw.print(produces);
				pw.print("\" ");
			}
			pw.println("{");
			r.forEach(pw::println);
			pw.println("}");
			pw.println("```");
			pw.println();
		});

		final var rights = ep.getRights();
		if (rights.isEmpty() == false) {
			pw.print("_Mandatory rights: ");
			pw.println(rights + "_");
			pw.println();
		}

		pw.print("[Go to the top](#rest-api)");
		pw.print(" &bull; [" + ep.getControllerSimpleName());
		pw.print(" :: ");
		pw.print(ep.getMethodName());
		pw.print("](/blob/master/");
		pw.print(ep.getControllerFullPath());
		pw.print("#");
		pw.print(ep.getLineMethodInController());
		pw.print(")");
	}

}
