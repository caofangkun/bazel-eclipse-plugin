// Copyright 2016 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.github.caofangkun.bazelipse.wizard;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class NewBazelProjectWizard extends BasicNewProjectResourceWizard
    implements IWorkbenchWizard, IExecutableExtension {

  private IConfigurationElement config;
  protected WizardNewProjectCreationPage page1;
  protected WorkspaceWizardPage page2;

  public NewBazelProjectWizard() {
    setWindowTitle("New Bazel Project Wizard");
  }

  @Override
  public void addPages() {
    page1 =
        new WizardNewProjectCreationPage("Creating a new Bazel import project");
    page2 = new WorkspaceWizardPage();
    addPage(page1);
    addPage(page2);
  }

  @Override
  public boolean performFinish() {
    BazelProjectSupport.createProject(page1.getProjectName(),
        page1.getLocationURI(), page2.getWorkspaceRoot(),
        page2.getDirectories(), page2.getTargets());
    BasicNewProjectResourceWizard.updatePerspective(config);
    return true;
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
  }

}
