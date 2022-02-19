/**
*	Copyright 2021 Onsiea All rights reserved.
*
*	This file is part of OnsieaLogger. (https://github.com/Onsiea/OnsieaLogger)
*
*	Unless noted in license (https://github.com/Onsiea/OnsieaLogger/blob/main/LICENSE.md) notice file (https://github.com/Onsiea/OnsieaLogger/blob/main/LICENSE_NOTICE.md), OnsieaLogger and all parts herein is licensed under the terms of the LGPL-3.0 (https://www.gnu.org/licenses/lgpl-3.0.html)  found here https://www.gnu.org/licenses/lgpl-3.0.html and copied below the license file.
*
*	OnsieaLogger is libre software: you can redistribute it and/or modify
*	it under the terms of the GNU Lesser General Public License as published by
*	the Free Software Foundation, either version 3.0 of the License, or
*	(at your option) any later version.
*
*	OnsieaLogger is distributed in the hope that it will be useful,
*	but WITHOUT ANY WARRANTY; without even the implied warranty of
*	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*	GNU Lesser General Public License for more details.
*
*	You should have received a copy of the GNU Lesser General Public License
*	along with OnsieaLogger.  If not, see <https://www.gnu.org/licenses/> <https://www.gnu.org/licenses/lgpl-3.0.html>.
*
*	Neither the name "Onsiea", "OnsieaLogger", or any derivative name or the names of its authors / contributors may be used to endorse or promote products derived from this software and even less to name another project or other work without clear and precise permissions written in advance.
*
*	(more details on https://github.com/Onsiea/OnsieaLogger/wiki/License)
*
*	@author Seynax
*/
package fr.onsiea.logger.tag;

import java.util.HashMap;
import java.util.Map;

import fr.onsiea.logger.EnumSeverity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Seynax
 *
 */
@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PRIVATE)
public class TagFunctions
{
	private Map<String, ITagFunction> functions;

	public TagFunctions()
	{
		this.functions(new HashMap<>());
	}

	public TagFunctions add(String nameIn, ITagFunction functionIn)
	{
		this.functions().put(nameIn, functionIn);

		return this;
	}

	/**
	 * @param severityIn
	 * @param patternIn
	 * @param contentIn
	 * @param tagInfoIn
	 * @param currentStackTraceIn
	 * @return
	 */
	public String replace(EnumSeverity severityIn, String patternIn, String contentIn, TagInfo tagInfoIn,
			StackTraceElement currentStackTraceIn)
	{
		final var iterator = this.functions().entrySet().iterator();

		while (iterator.hasNext())
		{
			final var entry = iterator.next();

			if (tagInfoIn.name().toLowerCase().contentEquals(entry.getKey().toLowerCase()))
			{
				return entry.getValue().replace(severityIn, patternIn, contentIn, tagInfoIn, currentStackTraceIn);
			}
		}

		return null;
	}
}