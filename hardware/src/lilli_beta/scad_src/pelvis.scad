$fn = 64;
downConnectorCubes = [0.0199, 0.0037, 0.034];
downTransX = [0.01, 0.0599];
downTransY = [0.0052, 0.0537];
upConnectorCubes = [0.0199, 0.0037, 0.034];
upTransX = [0.0348];
upTransY = [0.1785, 0.13];


rotate([90,0,180]){
    union(){

        // Back-Front pages with empty space
        backAndFrontRectanglesWithSpace();
        
        // Up connectors
        upConnectors(upConnectorCubes, upTransX, upTransY);
        
        // Down connectors
        downConnectors(downConnectorCubes, downTransX, downTransY);
    }
}

module backAndFrontRectanglesWithSpace(){
        difference(){
            roundedcube(0.09, 0.19, 0.034, 0.01);
            translate([0,0, 0.003]){
                roundedcube(0.09, 0.19, 0.028, 0.01);
            }
            translate([0.0348, 0.135, 0]){
               cube([0.02, 0.0418, 0.031]);
            }
        
            // Rounded side
            roundedSide();
        }
    }

module roundedSide(){
        translate([0.065, 0.063, 0]){
            rotate([0, 0, -5]){
                roundedcube(0.09, 0.19, 0.034, 0.03);
            }
        }
            
        translate([-0.065, 0.055, 0]){
            rotate([0, 0, 5]){
                roundedcube(0.09, 0.19, 0.034, 0.03);
            }
        }
    }

module upConnectors(dims, upTransX, upTransY){
        translate([upTransX[0], upTransY[0], 0]){
            cube(dims);
        }
        translate([upTransX[0], upTransY[1], 0]){
            cube(dims);
        }
    }

module downConnectors(dims, transX, transY){
        translate([transX[0], transY[0], 0]){
            cube(dims);
        }
        translate([transX[1], transY[0], 0]){
            cube(dims);
        }
        translate([transX[0], transY[1], 0]){
            cube(dims);
        }
        translate([transX[1], transY[1], 0]){
            cube(dims);
        }
    }

module roundedcube(xdim, ydim, zdim, rdim){    
    hull(){
        translate([rdim, rdim, 0]) cylinder(r=rdim, h=zdim);
        translate([xdim-rdim, rdim, 0]) cylinder(r=rdim, h=zdim);
        
        translate([rdim, ydim-rdim, 0]) cylinder(r=rdim, h=zdim);
        translate([xdim-rdim, ydim-rdim, 0]) cylinder(r=rdim, h=zdim);
    }
}