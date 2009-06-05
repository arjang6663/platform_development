/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.sdklib.internal.repository;

import com.android.sdklib.SdkConstants;
import com.android.sdklib.internal.repository.Archive.Arch;
import com.android.sdklib.internal.repository.Archive.Os;

import org.w3c.dom.Node;

import java.io.File;

/**
 * Represents a tool XML node in an SDK repository.
 */
public class ToolPackage extends Package {

    /**
     * Creates a new tool package from the attributes and elements of the given XML node.
     * <p/>
     * This constructor should throw an exception if the package cannot be created.
     */
    ToolPackage(RepoSource source, Node packageNode) {
        super(source, packageNode);
    }

    /**
     * Manually create a new package with one archive and the given attributes.
     * This is used to create packages from local directories.
     */
    ToolPackage(RepoSource source,
            int revision,
            String license,
            String description,
            String descUrl,
            Os archiveOs,
            Arch archiveArch,
            String archiveUrl,
            long archiveSize,
            String archiveChecksum) {
        super(source,
                revision,
                license,
                description,
                descUrl,
                archiveOs,
                archiveArch,
                archiveUrl,
                archiveSize,
                archiveChecksum);
    }

    /** Returns a short description for an {@link IDescription}. */
    @Override
    public String getShortDescription() {
        return String.format("Android SDK Tools, revision %1$d", getRevision());
    }

    /** Returns a long description for an {@link IDescription}. */
    @Override
    public String getLongDescription() {
        return String.format("Android SDK Tools, revision %1$d.\n%2$s",
                getRevision(),
                super.getLongDescription());
    }

    /**
     * Computes a potential installation folder if an archive of this package were
     * to be installed right away in the given SDK root.
     * <p/>
     * A "tool" package should always be located in SDK/tools.
     *
     * @param osSdkRoot The OS path of the SDK root folder.
     * @return A new {@link File} corresponding to the directory to use to install this package.
     */
    @Override
    public File getInstallFolder(String osSdkRoot) {
        return new File(osSdkRoot, SdkConstants.FD_TOOLS);
    }
}
