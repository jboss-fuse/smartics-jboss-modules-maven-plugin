/*
 * Copyright 2013-2022 smartics, Kronseder & Reiner GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.smartics.maven.plugin.jboss.modules.aether.filter;

import java.util.List;

import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.graph.DependencyFilter;
import org.eclipse.aether.graph.DependencyNode;

/**
 * Rejects dependencies of type <tt>javadoc</tt>.
 */
public final class JavadocTypeFilter implements DependencyFilter
{
  // ********************************* Fields *********************************

  // --- constants ------------------------------------------------------------

  /**
   * The singleton instance since the filter has no state.
   */
  public static final JavadocTypeFilter INSTANCE = new JavadocTypeFilter();

  // --- members --------------------------------------------------------------

  // ****************************** Initializer *******************************

  // ****************************** Constructors ******************************

  // ****************************** Inner Classes *****************************

  // ********************************* Methods ********************************

  // --- init -----------------------------------------------------------------

  // --- get&set --------------------------------------------------------------

  // --- business -------------------------------------------------------------

  @Override
  public boolean accept(final DependencyNode node,
      final List<DependencyNode> parents)
  {
    final Dependency dependency = node.getDependency();
    if (dependency == null)
    {
      return false;
    }

    Artifact artifact = dependency.getArtifact();
    
    final String classifier = artifact.getClassifier();
    final boolean accept = !("javadoc".equals(classifier));
    if (!accept)
    {
      DependencyFlagger.INSTANCE.flag(node);
    }
    return accept;
  }

  // --- object basics --------------------------------------------------------

}
