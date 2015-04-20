/*
 * Copyright 2015 marlonlom
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

package co.malm.mglam_reads.mobile.events;

/**
 * Event class for handling selected category from navigation drawer
 *
 * @author marlonlom
 */
public class SelectedCategoryEvent {

    private Integer categoryPosition;
    private String categoryTitle;

    public SelectedCategoryEvent(Integer position, String title) {
        this.categoryPosition = position;
        this.categoryTitle = title;
    }

    public Integer getPosition() {
        return categoryPosition;
    }

    public void setPosition(Integer position) {
        this.categoryPosition = position;
    }

    public String getTitle() {
        return categoryTitle;
    }

    public void setTitle(String title) {
        this.categoryTitle = title;
    }
}
