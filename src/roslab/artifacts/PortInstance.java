/**
 * Copyright 2009-2011, Kansas State University, 
 * PRECISE Center at the University of Pennsylvania, and
 * Andrew King
 *
 *
 * This file is part of the Medical Device Coordination Framework (aka the MDCF)
 *
 *   The MDCF is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   The MDCF  is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with the MDCF .  If not, see <http://www.gnu.org/licenses/>.
 */
package roslab.artifacts;

public class PortInstance {
	
		public String moduleInstanceName;
		public String portName;
		public String portType;
		public ModuleSpec moduleSpec;
		
		public PortInstance(String moduleInstanceName, String portName,
				String portType, ModuleSpec moduleSpec) {
			super();
			this.moduleInstanceName = moduleInstanceName;
			this.portName = portName;
			this.moduleSpec = moduleSpec;
			this.portType = portType;
		}

		@Override
		public String toString(){
			return moduleInstanceName + "." + portName;
		}
		
		public String getIdent(){
			return moduleInstanceName + "." + portName;
		}
}
